//获取Android SDK目录
def get_SdkPath() {
    if (!rootProject.hasProperty('sdkPath')) {
        String DEF_PATH = "/opt/android-sdk-linux"
        String sdkPath = null
        Properties properties = localProperties
        if (properties != null) {
            sdkPath = properties.getProperty('sdk.dir')
            println("sdk.dir: $sdkPath")
        }
        if (sdkPath == null) {
            sdkPath = System.getenv()['ANDROID_HOME']
            println("ANDROID_HOME: $sdkPath")
        }
        if (sdkPath == null) {
            sdkPath = System.getenv()['ANDROID_SDK']
            println("ANDROID_SDK: $sdkPath")
        }
        if (sdkPath == null) {
            sdkPath = DEF_PATH
            println("DEF_SDK: $sdkPath")
        }
        //println("sdkPath: ${sdkPath}")
        return sdkPath
    }
    return sdkPath
}

//是否能访问Google
def get_GoogleConnected() {
    if (!rootProject.hasProperty('googleConnected')) {
        //def proxy = new Proxy(Proxy.Type.SOCKS, new InetSocketAddress("127.0.0.1", 1080))

        def connection = new URL("https://www.google.com").openConnection()
        def time = System.currentTimeMillis()
        try {

            // Plan A
            connection.connectTimeout = 3 *1000
            connection.connect()
            def success = connection.responseCode == 200
            println "googleConnected: ${connection.responseCode}, $success, ${System.currentTimeMillis() -time}"
            return success
        } catch (Throwable e) {

            // Plan B
            //time = System.currentTimeMillis()
            //def result = 'curl -I -m 3 -o /dev/null -s -w %{http_code} www.google.com'.execute().text
            //def success = result == '200'
            //println "googleConnected: $result, $success, ${System.currentTimeMillis() -time}"
            //return success

            println "googleConnected: ${e.message}, ${System.currentTimeMillis() -time}"
            return false
        }
    }
    return googleConnected
}

def _localMavenable = false
def _localMavenHost = 'http://192.168.1.187:8081'
def _defaultMaven = 'http://maven.aliyun.com/nexus/content/groups/public'
def _uploadUserName = 'admin'
def _uploadPassword = 'admin123'

def _androidcfg = [
        pluginVersion    : "3.0.0",
        //////////////////////////////////
        buildToolsVersion: "27.0.3",
        compileSdkVersion: 27,
        minSdkVersion    : 20,
        targetSdkVersion : 27,
        versionCode      : 1,
        versionName      : "1.0.0",
        supportVersion   : "27.+",
        kotlin_version   : "1.3.31",
        anko_version     : "0.10.8",
]

ext {
    //解析properties文件
    Closure loadProperties = { File f, String name = '', Project project = rootProject ->
        project.with {
            if (hasProperty(name)) return property(name)
            println "loadProperties: ${name}, ${f.canonicalPath}"
            new Properties().with { if (f.isFile()) f.withInputStream { load(it) }; delegate }
        }
    }

    //解析properties文件参数
    Closure confProperty = { String key, Object value ->
        rootProject.with {
            if (hasProperty(key)) return property(key)
            //local.properties > config.properties
            localProperties.getOrDefault(key, configProperties.getOrDefault(key, value))
        }
    }

    sdkPath = _SdkPath
    googleConnected = _GoogleConnected
    userHome = System.properties['user.home'] //用户目录
    println "userHome: $userHome"
    gradleProperties = loadProperties(rootProject.file('gradle.properties'), 'gradleProperties') as Properties
    configProperties = loadProperties(rootProject.file('config.properties'), 'configProperties') as Properties
    localProperties = loadProperties(rootProject.file('local.properties'), 'localProperties') as Properties

    configPath = confProperty('configPath',  "$rootDir/modules/github/wittyneko/gradle-tools")
    if (configPath.startsWith('~')) {
        configPath = "${userHome}${configPath.substring(1)}"
    }
    if (configPath.startsWith('rootDir')) {
        configPath = "${rootDir}${configPath.substring(7)}"
    }
    println "configPath: $configPath"

    localMavenable = Boolean.valueOf(confProperty('localMavenable', _localMavenable))
    localMavenHost = confProperty('localMavenHost', _localMavenHost)
    defaultMaven = confProperty('defaultMaven', _defaultMaven)
    uploadUserName = confProperty('uploadUserName', _uploadUserName)
    uploadPassword = confProperty('uploadPassword', _uploadPassword)

    androidcfg = [
            pluginVersion    : confProperty('androidcfg.pluginVersion', _androidcfg.pluginVersion),
            //////////////////////////////////
            buildToolsVersion: confProperty('androidcfg.buildToolsVersion', _androidcfg.buildToolsVersion),
            compileSdkVersion: confProperty('androidcfg.compileSdkVersion', _androidcfg.compileSdkVersion) as Integer,
            minSdkVersion    : confProperty('androidcfg.minSdkVersion', _androidcfg.minSdkVersion) as Integer,
            targetSdkVersion : confProperty('androidcfg.targetSdkVersion', _androidcfg.targetSdkVersion) as Integer,
            versionCode      : confProperty('androidcfg.versionCode', _androidcfg.versionCode) as Integer,
            versionName      : confProperty('androidcfg.versionName', _androidcfg.versionName),
            supportVersion   : confProperty('androidcfg.supportVersion', _androidcfg.supportVersion),
            kotlin_version   : confProperty('androidcfg.kotlin_version', _androidcfg.kotlin_version),
            anko_version     : confProperty('androidcfg.anko_version', _androidcfg.anko_version),
    ]

    maven = [
            userMaven          : "file:${userHome}/.user_maven/repository",
            userMavenSnapshots : "file:${userHome}/.user_maven/snapshotRepository",
            gradleCache        : "file:${userHome}/.gradle/caches/modules-2/files-2.1",
            localGroup         : localMavenable ? "$localMavenHost/nexus/content/groups/public" : defaultMaven,
            localMaven         : "$localMavenHost/nexus/content/repositories/releases",
            localMavenSnapshots: "$localMavenHost/nexus/content/repositories/snapshots",
            sdkMaven           : "file:${sdkPath}/extras/m2repository",
            sdkAndroidMaven    : "file:${sdkPath}/extras/android/m2repository",
            sdkGoogleMaven     : "file:${sdkPath}/extras/google/m2repository",
            aliyunMaven        : "https://maven.aliyun.com/repository/public",
            jcenter            : "http://jcenter.bintray.com",
            mavenCenter        : "https://repo1.maven.org/maven2/",
            googleMaven        : "https://maven.google.com",
            jitpack            : "https://jitpack.io",
            igexin             : "http://mvn.gt.igexin.com/nexus/content/repositories/releases/",
    ]

    uploadMaven = localMavenable ? maven.localMaven : maven.userMaven
    uploadMavenSnapshots = localMavenable ? maven.localMaven : maven.userMavenSnapshots

}
