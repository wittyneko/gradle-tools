# gradle-tools

#### 项目介绍
gradle-tools 简化打包和统一版本

#### 软件架构
软件架构说明


#### 安装教程

#### 使用说明

1). `config.groovy` Android 编译环境和Maven库配置

- 直接使用
```gardle
buildscript {
    apply from: "https://gitee.com/wittyneko/gradle-tools/raw/master/config.groovy"
    ext.kotlin_version = androidcfg.kotlin_version
    repositories {
        maven {url maven.aliyunMaven}
        google()
        jcenter()
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:3.1.3'
        classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"
        classpath 'org.jetbrains.dokka:dokka-android-gradle-plugin:0.9.15'

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

task clean(type: Delete) {
    delete rootProject.buildDir
}

```
- 复制到项目根目录，替换链接为如下其中一个
```gradle
apply from: "config.groovy"
apply from: "$rootDir/config.groovy"
```

2). `upload.gradle` 打包上传到Maven库

- 直接使用
```gradle
apply from: "https://gitee.com/wittyneko/gradle-tools/raw/master/upload.gradle"

group = "cn.wittyneko"
archivesBaseName = "ktx-base"
version = "1.0.0-SNAPSHOT"
```
- 复制到项目根目录
```gradle
apply from: "$rootDir/upload.gradle"

group = "cn.wittyneko"
archivesBaseName = "ktx-base"
version = "1.0.0-SNAPSHOT"
```

#### 参与贡献

1. Fork 本项目
2. 新建 Feat_xxx 分支
3. 提交代码
4. 新建 Pull Request


#### 码云特技

1. 使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2. 码云官方博客 [blog.gitee.com](https://blog.gitee.com)
3. 你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解码云上的优秀开源项目
4. [GVP](https://gitee.com/gvp) 全称是码云最有价值开源项目，是码云综合评定出的优秀开源项目
5. 码云官方提供的使用手册 [http://git.mydoc.io/](http://git.mydoc.io/)
6. 码云封面人物是一档用来展示码云会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)