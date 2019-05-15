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

ext {
    userHome = _UserHome
    configProperties = _ConfigProperties
    localProperties = _LocalProperties
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