//获取系统用户目录
def get_UserHome() {
    String home = System.properties['user.home']
    println("userHome: $home")
    return home
}

def get_ConfigProperties() {
    Properties properties = new Properties()
    File f = rootProject.file('config.properties')
    if (f.isFile()) {
        println("configProperties: ${f.absolutePath}")
        properties.load(f.newDataInputStream())
    }
    return properties
}

//获取local.properties解析
def get_LocalProperties() {
    Properties properties = new Properties()
    File f = rootProject.file('local.properties')
    if (f.isFile()) {
        println("localProperties: ${f.absolutePath}")
        properties.load(f.newDataInputStream())
    }
    return properties
}

//获取Android SDK目录
def get_SdkPath() {
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

def __configPath = "$rootDir"

def __localMavenable = false
def __localMavenHost = 'http://192.168.1.187:8081'
def __defaultMaven = 'http://maven.aliyun.com/nexus/content/groups/public'
def __uploadUserName = 'admin'
def __uploadPassword = 'admin123'

def __androidcfg = [
        pluginVersion    : "3.0.0",
        //////////////////////////////////
        compileSdkVersion: 27,
        buildToolsVersion: "27.0.3",
        minSdkVersion    : 20,
        targetSdkVersion : 27,
        versionCode      : 1,
        versionName      : "1.0.0",
        supportVersion   : "27.+",
        kotlin_version   : "1.2.+",
        anko_version     : "0.10.+",
]


def _configPath = _ConfigProperties.getProperty('configPath', __configPath)

def _localMavenable = _ConfigProperties.getOrDefault('localMavenable', __localMavenable)
def _localMavenHost = _ConfigProperties.getProperty('localMavenHost', __localMavenHost)
def _defaultMaven = _ConfigProperties.getProperty('defaultMaven', __defaultMaven)
def _uploadUserName = _ConfigProperties.getProperty('uploadUserName', __uploadUserName)
def _uploadPassword = _ConfigProperties.getProperty('uploadPassword', __uploadPassword)

def _androidcfg = [
        pluginVersion    : _ConfigProperties.getOrDefault('androidcfg.pluginVersion', __androidcfg.pluginVersion),
        //////////////////////////////////
        compileSdkVersion: _ConfigProperties.getOrDefault('androidcfg.compileSdkVersion', __androidcfg.compileSdkVersion),
        buildToolsVersion: _ConfigProperties.getOrDefault('androidcfg.buildToolsVersion', __androidcfg.buildToolsVersion),
        minSdkVersion    : _ConfigProperties.getOrDefault('androidcfg.minSdkVersion', __androidcfg.minSdkVersion),
        targetSdkVersion : _ConfigProperties.getOrDefault('androidcfg.targetSdkVersion', __androidcfg.targetSdkVersion),
        versionCode      : _ConfigProperties.getOrDefault('androidcfg.versionCode', __androidcfg.versionCode),
        versionName      : _ConfigProperties.getOrDefault('androidcfg.versionName', __androidcfg.versionName),
        supportVersion   : _ConfigProperties.getOrDefault('androidcfg.supportVersion', __androidcfg.supportVersion),
        kotlin_version   : _ConfigProperties.getOrDefault('androidcfg.kotlin_version', __androidcfg.kotlin_version),
        anko_version     : _ConfigProperties.getOrDefault('androidcfg.anko_version', __androidcfg.anko_version),
]

ext {
    userHome = _UserHome
    localProperties = _LocalProperties
    configProperties = _ConfigProperties
    sdkPath = _SdkPath
    configPath = localProperties.getProperty('configPath', _configPath)

    localMavenable = localProperties.getOrDefault('localMavenable', _localMavenable)
    localMavenHost = localProperties.getProperty('localMavenHost', _localMavenHost)
    defaultMaven = localProperties.getProperty('defaultMaven', _defaultMaven)
    uploadUserName = localProperties.getProperty('uploadUserName', _uploadUserName)
    uploadPassword = localProperties.getProperty('uploadPassword', _uploadPassword)

    androidcfg = [
            properties       : localProperties,
            sdkPath          : sdkPath,
            pluginVersion    : localProperties.getOrDefault('androidcfg.pluginVersion', _androidcfg.pluginVersion),
            //////////////////////////////////
            compileSdkVersion: localProperties.getOrDefault('androidcfg.compileSdkVersion', _androidcfg.compileSdkVersion),
            buildToolsVersion: localProperties.getOrDefault('androidcfg.buildToolsVersion', _androidcfg.buildToolsVersion),
            minSdkVersion    : localProperties.getOrDefault('androidcfg.minSdkVersion', _androidcfg.minSdkVersion),
            targetSdkVersion : localProperties.getOrDefault('androidcfg.targetSdkVersion', _androidcfg.targetSdkVersion),
            versionCode      : localProperties.getOrDefault('androidcfg.versionCode', _androidcfg.versionCode),
            versionName      : localProperties.getOrDefault('androidcfg.versionName', _androidcfg.versionName),
            supportVersion   : localProperties.getOrDefault('androidcfg.supportVersion', _androidcfg.supportVersion),
            kotlin_version   : localProperties.getOrDefault('androidcfg.kotlin_version', _androidcfg.kotlin_version),
            anko_version     : localProperties.getOrDefault('androidcfg.anko_version', _androidcfg.anko_version),
    ]

    maven = [
            userMaven          : "file:${userHome}/.user_maven/repository",
            userMavenSnapshots : "file:${userHome}/.user_maven/snapshotRepository",
            gradleCache        : "file:${userHome}/.gradle/caches/modules-2/files-2.1",
            localGroup         : localMavenable ? "$localMavenHost/nexus/content/groups/public" : defaultMaven,
            localMaven         : "$localMavenHost/nexus/content/repositories/releases",
            localMavenSnapshots: "$localMavenHost/nexus/content/repositories/snapshots",
            sdkMaven           : "file:${androidcfg.sdkPath}/extras/m2repository",
            sdkAndroidMaven    : "file:${androidcfg.sdkPath}/extras/android/m2repository",
            sdkGoogleMaven     : "file:${androidcfg.sdkPath}/extras/google/m2repository",
            aliyunMaven        : "http://maven.aliyun.com/nexus/content/groups/public",
            jcenter            : "http://jcenter.bintray.com",
            mavenCenter        : "https://repo1.maven.org/maven2/",
            googleMaven        : "https://maven.google.com",
            jitpack            : "https://jitpack.io",
            igexin             : "http://mvn.gt.igexin.com/nexus/content/repositories/releases/",
    ]

    uploadMaven = localMavenable ? maven.localMaven : maven.userMaven
    uploadMavenSnapshots = localMavenable ? maven.localMaven : maven.userMavenSnapshots

}