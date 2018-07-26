# gradle-tools

#### 项目介绍

`gradle-tools` 能帮助简化 `Gradle` 配置和统一管理依赖版本

#### 结构说明

- `properties.groovy` 用于读取 `.properties` 文件的扩展工具
- `config.groovy` 读取解析 Android 版本配置和管理 Maven 仓库地址
- `dependencies.groovy` 依懒版本管理
- `upload.gradle` 打包工具
- `config.properties` 配置文件模板

其中 `properties.groovy` 和 `config.groovy` 可独立使用，`dependencies.groovy` 和 `upload.gradle` 依懒前者的扩展属性

配置解析优先级 `local.properties` > `config.properties` > `config.groovy`

#### 导入说明
参考例子[ktx-base](https://github.com/wittyneko/ktx-base)

`gradle-tools` 提供多种导入方式，修改根项目 `build.gradle` 以下分别进行介绍

1). **通过网络引用**

```gradle
buildscript {

    ...

    //apply from: "https://gitee.com/wittyneko/gradle-tools/raw/gitee/properties.groovy"
    apply from: "https://github.com/wittyneko/gradle-tools/raw/github/properties.groovy"
    apply from: "$configPath/config.groovy"
    apply from: "$configPath/dependencies.groovy"
    ext.kotlin_version = androidcfg.kotlin_version

    repositories {
        maven { url uploadMaven }
        maven { url uploadMavenSnapshots }
        maven { url rootProject.maven.aliyunMaven }
        google()
        jcenter()
    }

    ...

}

allprojects {
    repositories {
        maven { url uploadMaven }
        maven { url uploadMavenSnapshots }
        maven { url rootProject.maven.aliyunMaven }
        google()
        jcenter()
    }
}
```
最简单的方式，什么都不需要配置只要你能联网，目前支持[github](https://github.com/wittyneko/gradle-tools)或[gitee](https://gitee.com/wittyneko/gradle-tools) 引用

2). **项目内引用，将需要用到的文件复制到项目根目录**

```gradle
buildscript {

    ...

    apply from: "properties.groovy"
    apply from: "$configPath/config.groovy"
    apply from: "$configPath/dependencies.groovy"
    ext.kotlin_version = androidcfg.kotlin_version

    repositories {
        maven { url uploadMaven }
        maven { url uploadMavenSnapshots }
        maven { url rootProject.maven.aliyunMaven }
        google()
        jcenter()
    }

    ...

}

allprojects {
    repositories {
        maven { url uploadMaven }
        maven { url uploadMavenSnapshots }
        maven { url rootProject.maven.aliyunMaven }
        google()
        jcenter()
    }
}
```
简单粗暴，不需要担心网络问题可随意修改脚本，但是新功能和优化更新不及时，对比前面配置只改了个链接

3). **指定引用**

- 克隆当前项目到指定位置( eg. 系统用户目录 )
- 复制 `properties.groovy` 和 `config.properties` 到项目根目录
- 修改 `config.properties` 的 `configPath` 为文件所在路径( eg. `configPath=~/gradle-tools` )，`~` 指代系统用户目录
- 配置与第二种相同

当然配置也可以设置在 `local.properties` 如果都存在优先使用 `local.properties` 的配置，优先级在结构说明已经介绍

这样设计是为了保证通用性，`config.properties` 指定为网络引用提交到远程仓库，
别人可直接编译运行，`local.properties` 指定到本地引用可离线使用

#### 使用说明

1). **config.groovy 编译环境和Maven库配置**

修改 Android 模块的 `build.gradle` 配置为如下
```gradle

android {
    compileSdkVersion androidcfg.compileSdkVersion

    defaultConfig {
        minSdkVersion androidcfg.minSdkVersion
        targetSdkVersion androidcfg.targetSdkVersion
        versionCode androidcfg.versionCode
        versionName androidcfg.versionName

        testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
    }
    
    ...
}

```
之后需要修改版本的时候只要修改 `config.properties` 或 `local.properties` 的配置，
再也不用担心 Android Studio 修改了 gradle 脚本需要同步工程了，两个配置文件规则同上

2). **upload.gradle 打包上传到Maven库**

```gradle
apply from: "$configPath/upload.gradle"

group = "cn.wittyneko"
archivesBaseName = "ktx-base"
version = "1.0.0-SNAPSHOT"
```
打包上传到 `Maven` 仓库的繁琐配置就此解决

3). **dependencies.groovy 管理依懒**

之后再补上, 这个应该很多人都知道

#### 参与贡献

1. Fork 本项目
2. 新建 Feat_xxx 分支
3. 提交代码
4. 新建 Pull Request