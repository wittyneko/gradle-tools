//获取系统用户目录
def get_UserHome() {
    if (!rootProject.hasProperty('userHome')) {
        String home = System.properties['user.home']
        println("userHome: $home")
        return home
    }
    userHome
}

//config.properties解析
def get_ConfigProperties() {
    if (!rootProject.hasProperty('configProperties')) {

        def properties = new Properties()
        def f = rootProject.file('config.properties')
        if (f.isFile()) {
            println("configProperties: ${f.absolutePath}")
            f.withInputStream {
                properties.load(it)
            }
        }
        return properties
    }
    return configProperties
}

//获取local.properties解析
def get_LocalProperties() {
    if (!rootProject.hasProperty('localProperties')) {
        Properties properties = new Properties()
        File f = rootProject.file('local.properties')
        if (f.isFile()) {
            println("localProperties: ${f.absolutePath}")
            f.withInputStream {
                properties.load(it)
            }
        }
        return properties
    }
    return localProperties
}

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

ext {
    userHome = _UserHome
    configProperties = _ConfigProperties
    localProperties = _LocalProperties
    sdkPath = _SdkPath
    googleConnected = _GoogleConnected
}

ext {
    if (!rootProject.hasProperty('configPath')) {
        def __configPath = "$rootDir/modules/github/wittyneko/gradle-tools"
        def _configPath = _ConfigProperties.getProperty('configPath', __configPath)
        configPath = _LocalProperties.getProperty('configPath', _configPath)
        if (configPath.startsWith('~')) {
            configPath = "${userHome}${configPath.substring(1)}"
        }
        if (configPath.startsWith('rootDir')) {
            configPath = "${rootDir}${configPath.substring(7)}"
        }
        println configPath
    }
}

def __localMavenable = false
def __localMavenHost = 'http://192.168.1.187:8081'
def __defaultMaven = 'http://maven.aliyun.com/nexus/content/groups/public'
def __uploadUserName = 'admin'
def __uploadPassword = 'admin123'

def __androidcfg = [
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



def _localMavenable = _ConfigProperties.getOrDefault('localMavenable', __localMavenable)
def _localMavenHost = _ConfigProperties.getProperty('localMavenHost', __localMavenHost)
def _defaultMaven = _ConfigProperties.getProperty('defaultMaven', __defaultMaven)
def _uploadUserName = _ConfigProperties.getProperty('uploadUserName', __uploadUserName)
def _uploadPassword = _ConfigProperties.getProperty('uploadPassword', __uploadPassword)

def _androidcfg = [
        pluginVersion    : _ConfigProperties.getOrDefault('androidcfg.pluginVersion', __androidcfg.pluginVersion),
        //////////////////////////////////
        buildToolsVersion: _ConfigProperties.getOrDefault('androidcfg.buildToolsVersion', __androidcfg.buildToolsVersion),
        compileSdkVersion: _ConfigProperties.getOrDefault('androidcfg.compileSdkVersion', __androidcfg.compileSdkVersion) as Integer,
        minSdkVersion    : _ConfigProperties.getOrDefault('androidcfg.minSdkVersion', __androidcfg.minSdkVersion) as Integer,
        targetSdkVersion : _ConfigProperties.getOrDefault('androidcfg.targetSdkVersion', __androidcfg.targetSdkVersion) as Integer,
        versionCode      : _ConfigProperties.getOrDefault('androidcfg.versionCode', __androidcfg.versionCode) as Integer,
        versionName      : _ConfigProperties.getOrDefault('androidcfg.versionName', __androidcfg.versionName),
        supportVersion   : _ConfigProperties.getOrDefault('androidcfg.supportVersion', __androidcfg.supportVersion),
        kotlin_version   : _ConfigProperties.getOrDefault('androidcfg.kotlin_version', __androidcfg.kotlin_version),
        anko_version     : _ConfigProperties.getOrDefault('androidcfg.anko_version', __androidcfg.anko_version),
]

ext {

    localMavenable = Boolean.valueOf(_LocalProperties.getOrDefault('localMavenable', _localMavenable))
    localMavenHost = _LocalProperties.getProperty('localMavenHost', _localMavenHost)
    defaultMaven = _LocalProperties.getProperty('defaultMaven', _defaultMaven)
    uploadUserName = _LocalProperties.getProperty('uploadUserName', _uploadUserName)
    uploadPassword = _LocalProperties.getProperty('uploadPassword', _uploadPassword)

    androidcfg = [
            pluginVersion    : _LocalProperties.getOrDefault('androidcfg.pluginVersion', _androidcfg.pluginVersion),
            //////////////////////////////////
            buildToolsVersion: _LocalProperties.getOrDefault('androidcfg.buildToolsVersion', _androidcfg.buildToolsVersion),
            compileSdkVersion: _LocalProperties.getOrDefault('androidcfg.compileSdkVersion', _androidcfg.compileSdkVersion) as Integer,
            minSdkVersion    : _LocalProperties.getOrDefault('androidcfg.minSdkVersion', _androidcfg.minSdkVersion) as Integer,
            targetSdkVersion : _LocalProperties.getOrDefault('androidcfg.targetSdkVersion', _androidcfg.targetSdkVersion) as Integer,
            versionCode      : _LocalProperties.getOrDefault('androidcfg.versionCode', _androidcfg.versionCode) as Integer,
            versionName      : _LocalProperties.getOrDefault('androidcfg.versionName', _androidcfg.versionName),
            supportVersion   : _LocalProperties.getOrDefault('androidcfg.supportVersion', _androidcfg.supportVersion),
            kotlin_version   : _LocalProperties.getOrDefault('androidcfg.kotlin_version', _androidcfg.kotlin_version),
            anko_version     : _LocalProperties.getOrDefault('androidcfg.anko_version', _androidcfg.anko_version),
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