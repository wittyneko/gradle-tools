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
            localProperties.getProperty(key, configProperties.getProperty(key, value))
        }
    }

    userHome = System.properties['user.home'] //用户目录
    println "userHome: $userHome"
    gradleProperties = loadProperties(rootProject.file('gradle.properties'), 'gradleProperties') as Properties
    configProperties = loadProperties(rootProject.file('config.properties'), 'configProperties') as Properties
    localProperties = loadProperties(rootProject.file('local.properties'), 'localProperties') as Properties

    configPath = confProperty('configPath', "https://github.com/wittyneko/gradle-tools/raw/master")
    if (configPath.startsWith('~')) {
        configPath = "${userHome}${configPath.substring(1)}"
    }
    println "configPath: $configPath"
}
