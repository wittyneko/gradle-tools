//获取系统用户目录
def get_UserHome() {
    String home = System.properties['user.home']
    println("userHome: $home")
    return home
}

//获取local.properties解析
def get_LocalProperties() {
    Properties properties = null
    File f = rootProject.file('local.properties')
    if (f.exists()) {
        println("localProperties: ${f.absolutePath}")
        properties = new Properties()
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

ext {
    userHome = _UserHome
    localProperties = _LocalProperties
    sdkPath = _SdkPath
    localMavenHost = "http://192.168.1.187:8081"
    defaultMaven = "http://maven.aliyun.com/nexus/content/groups/public"
    localMavenable = localProperties.getOrDefault('localMavenable', false)
    uploadUserName = localProperties.getProperty('uploadUserName', 'admin')
    uploadPassword = localProperties.getProperty('uploadPassword', 'admin123')

    androidcfg = [
            pluginVersion    : "3.0.0",
            properties       : localProperties,
            sdkPath          : sdkPath,
            //////////////////////////////////
            compileSdkVersion: 27,
            buildToolsVersion: "26.0.2",
            minSdkVersion    : 20,
            targetSdkVersion : 27,
            versionCode      : 1,
            versionName      : "1.0.0",
            supportVersion   : "27.+",
            kotlin_version    : "1.2.+",
            anko_version     : "0.10.+",
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