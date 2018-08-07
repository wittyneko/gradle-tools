apply from: "$configPath/config.groovy"
ext {

    dependencies_ver = [
            "support"          : androidcfg.supportVersion,
            "kotlin"           : androidcfg.kotlin_version,
            "anko"             : androidcfg.anko_version,

            "lifecycle_version": "1.1.1",
            "rxlifecycle"      : "0.6.1",
            "rxlifecycle2"     : "2.0.1",
            "rxbinding"        : "1.0.1",
            "rxbinding2"       : "2.0.0",
            "dagger2"          : "2.11",
            "retrofit"         : "2.3.0",
            "glide"            : "4.3.1",
    ]

    dependencies_map = [

            ////// ====== system ====== ///////

            "multidex"                       : "com.android.support:multidex:+",
            "constraint-layout"              : "com.android.support.constraint:constraint-layout:1.1.2",
            "percent"                        : "com.android.support:percent:${dependencies_ver['support']}",
            "support-v4"                     : "com.android.support:support-v4:${dependencies_ver['support']}",
            "appcompat-v7"                   : "com.android.support:appcompat-v7:${dependencies_ver['support']}",
            "annotations"                    : "com.android.support:support-annotations:${dependencies_ver['support']}",
            "design"                         : "com.android.support:design:${dependencies_ver['support']}",
            "cardview-v7"                    : "com.android.support:cardview-v7:${dependencies_ver['support']}",
            "recyclerview-v7"                : "com.android.support:recyclerview-v7:${dependencies_ver['support']}",
            "support-annotations"            : "com.android.support:support-annotations:${dependencies_ver['support']}",

            // ViewModel and LiveData
            "lifecycle-extensions"           : "android.arch.lifecycle:extensions:${dependencies_ver['lifecycle_version']}",
            "lifecycle-runtime"              : "android.arch.lifecycle:runtime:${dependencies_ver['lifecycle_version']}",
            "lifecycle-common-java8"         : "android.arch.lifecycle:common-java8:${dependencies_ver['lifecycle_version']}",

            "kotlin-stdlib"                  : "org.jetbrains.kotlin:kotlin-stdlib:${dependencies_ver['kotlin']}",
            "kotlin-stdlib-jdk7"             : "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${dependencies_ver['kotlin']}",

            // Anko
            "anko"                           : "org.jetbrains.anko:anko:${dependencies_ver['anko']}",
            "anko-commons"                   : "org.jetbrains.anko:anko-commons:${dependencies_ver['anko']}",
            "anko-sdk25"                     : "org.jetbrains.anko:anko-sdk25:${dependencies_ver['anko']}",
            "anko-appcompat-v7"              : "org.jetbrains.anko:anko-appcompat-v7:${dependencies_ver['anko']}",
            "anko-sdk25-coroutines"          : "org.jetbrains.anko:anko-sdk25-coroutines:${dependencies_ver['anko']}",
            "anko-appcompat-v7-couroutines"  : "org.jetbrains.anko:anko-appcompat-v7-couroutines:${dependencies_ver['anko']}",
            "anko-sqlite"                    : "org.jetbrains.anko:anko-sqlite:${dependencies_ver['anko']}",

            // Anko Android support libraries
            "anko-appcompat-v7-commons"      : "org.jetbrains.anko:anko-appcompat-v7-commons:${dependencies_ver['anko']}",
            "anko-coroutines"                : "org.jetbrains.anko:anko-coroutines:${dependencies_ver['anko']}",
            "anko-cardview-v7"               : "org.jetbrains.anko:anko-cardview-v7:${dependencies_ver['anko']}",
            "anko-design"                    : "org.jetbrains.anko:anko-design:${dependencies_ver['anko']}",
            "anko-design-coroutines"         : "org.jetbrains.anko:anko-design-coroutines:${dependencies_ver['anko']}",
            "anko-gridlayout-v7"             : "org.jetbrains.anko:anko-gridlayout-v7:${dependencies_ver['anko']}",
            "anko-percent"                   : "org.jetbrains.anko:anko-percent:${dependencies_ver['anko']}",
            "anko-recyclerview-v7"           : "org.jetbrains.anko:anko-recyclerview-v7:${dependencies_ver['anko']}",
            "anko-recyclerview-v7-coroutines": "org.jetbrains.anko:anko-recyclerview-v7-coroutines:${dependencies_ver['anko']}",
            "anko-support-v4-commons"        : "org.jetbrains.anko:anko-support-v4-commons:${dependencies_ver['anko']}",
            "anko-support-v4"                : "org.jetbrains.anko:anko-support-v4:${dependencies_ver['anko']}",

            ////// ======= framework ====== //////

            //RxJava 1.0
            "rxjava"                         : "io.reactivex:rxjava:1.3.0",
            "rxandroid"                      : "io.reactivex:rxandroid:1.2.1",

            "rxbinding"                      : "com.jakewharton.rxbinding:rxbinding:${dependencies_ver['rxbinding']}",
            "rxbinding-support-v4"           : "com.jakewharton.rxbinding:rxbinding-support-v4:${dependencies_ver['rxbinding']}",
            "rxbinding-appcompat-v7"         : "com.jakewharton.rxbinding:rxbinding-appcompat-v7:${dependencies_ver['rxbinding']}",
            "rxbinding-recyclerview-v7"      : "com.jakewharton.rxbinding:rxbinding-recyclerview-v7:${dependencies_ver['rxbinding']}",
            "rxbinding-leanback-v17"         : "com.jakewharton.rxbinding:rxbinding-leanback-v17:${dependencies_ver['rxbinding']}",
            "rxbinding-design"               : "com.jakewharton.rxbinding:rxbinding-design:${dependencies_ver['rxbinding']}",

            "rxlifecycle"                    : "com.trello:rxlifecycle:${dependencies_ver['rxlifecycle']}",
            "rxlifecycle-components"         : "com.trello:rxlifecycle-components:${dependencies_ver['rxlifecycle']}",
            "rxlifecycle-kotlin"             : "com.trello:rxlifecycle-kotlin:${dependencies_ver['rxlifecycle']}",
            "rxlifecycle-navi"               : "com.trello:rxlifecycle-navi:${dependencies_ver['rxlifecycle']}",

            "rxcache"                        : "com.github.VictorAlbertos.RxCache:runtime:1.7.0-1.x",
            "rxcache-jolyglot-gson"          : "com.github.VictorAlbertos.Jolyglot:gson:0.0.3",
            "rxpermissions"                  : "com.tbruyelle.rxpermissions:rxpermissions:0.9.4@aar",
            "rxerrorhandler"                 : "me.jessyan:rxerrorhandler:1.0.1",

            // RxJava 2.0
            "rxjava2"                        : "io.reactivex.rxjava2:rxjava:2.0.1",
            "rxandroid2"                     : "io.reactivex.rxjava2:rxandroid:2.0.1",

            "rxbinding2"                     : "com.jakewharton.rxbinding2:rxbinding:${dependencies_ver['rxbinding2']}",
            "rxbinding2-support-v4"          : "com.jakewharton.rxbinding2:rxbinding-support-v4:${dependencies_ver['rxbinding2']}",
            "rxbinding2-appcompat-v7"        : "com.jakewharton.rxbinding2:rxbinding-appcompat-v7:${dependencies_ver['rxbinding2']}",
            "rxbinding2-recyclerview-v7"     : "com.jakewharton.rxbinding2:rxbinding-recyclerview-v7:${dependencies_ver['rxbinding2']}",
            "rxbinding2-leanback-v17"        : "com.jakewharton.rxbinding2:rxbinding-leanback-v17:${dependencies_ver['rxbinding2']}",
            "rxbinding2-design"              : "com.jakewharton.rxbinding2:rxbinding-design:${dependencies_ver['rxbinding2']}",

            "rxlifecycle2"                   : "com.trello.rxlifecycle2:rxlifecycle:${dependencies_ver['rxlifecycle2']}",
            "rxlifecycle2-android"           : "com.trello.rxlifecycle2:rxlifecycle-android:${dependencies_ver['rxlifecycle2']}",
            "rxlifecycle2-components"        : "com.trello.rxlifecycle2:rxlifecycle-components:${dependencies_ver['rxlifecycle2']}",
            "rxlifecycle2-kotlin"            : "com.trello.rxlifecycle2:rxlifecycle-kotlin:${dependencies_ver['rxlifecycle2']}",
            "rxlifecycle2-navi"              : "com.trello.rxlifecycle2:rxlifecycle-navi:${dependencies_ver['rxlifecycle2']}",

            "rxcache2"                       : "com.github.VictorAlbertos.RxCache:runtime:1.8.0-2.x",
            "rxpermissions2"                 : "com.tbruyelle.rxpermissions2:rxpermissions:0.9.4@aar",
            "rxerrorhandler2"                : "me.jessyan:rxerrorhandler:2.0.2",

            //dagger2
            "dagger"                         : "com.google.dagger:dagger:${dependencies_ver['dagger2']}",
            "dagger-compiler"                : "com.google.dagger:dagger-compiler:${dependencies_ver['dagger2']}",
            "dagger-android"                 : "com.google.dagger:dagger-android:${dependencies_ver['dagger2']}",
            "dagger-android-support"         : "com.google.dagger:dagger-android-support:${dependencies_ver['dagger2']}",
            "dagger-android-processor"       : "com.google.dagger:dagger-android-processor:${dependencies_ver['dagger2']}",

            //网络
            "okhttp3"                        : "com.squareup.okhttp3:okhttp:3.8.0",
            "logging-interceptor"            : "com.squareup.okhttp3:logging-interceptor:3.8.0",
            "okhttp-urlconnection"           : "com.squareup.okhttp:okhttp-urlconnection:2.0.0",
            "retrofit"                       : "com.squareup.retrofit2:retrofit:${dependencies_ver['retrofit']}",
            "retrofit-converter-gson"        : "com.squareup.retrofit2:converter-gson:${dependencies_ver['retrofit']}",
            "retrofit-adapter-rxjava"        : "com.squareup.retrofit2:adapter-rxjava:${dependencies_ver['retrofit']}",
            "retrofit-adapter-rxjava2"       : "com.squareup.retrofit2:adapter-rxjava2:${dependencies_ver['retrofit']}",

            //图片加载
            "glide3.x"                       : 'com.github.bumptech.glide:glide:3.7.0',
            "glide"                          : "com.github.bumptech.glide:glide:${dependencies_ver["glide"]}",
            "glide-compiler"                 : "com.github.bumptech.glide:compiler:${dependencies_ver["glide"]}",
            "glide-loader-okhttp3"           : "com.github.bumptech.glide:okhttp3-integration:${dependencies_ver["glide"]}",
            "picasso"                        : 'com.squareup.picasso:picasso:2.5.2',
            "fresco"                         : 'com.facebook.fresco:fresco:0.12.0',

            "gson"                           : 'com.google.code.gson:gson:2.2.4',
            "nineoldandroids"                : 'com.nineoldandroids:library:2.4.0',
            "eventbus"                       : "org.simple:androideventbus:1.0.5.1",

            ////// ====== library ====== //////

            "javax.annotation"               : "javax.annotation:jsr250-api:1.0",
            "androideventbus"                : "org.simple:androideventbus:1.0.5.1",
            "android-gif-drawable"           : 'pl.droidsonroids.gif:android-gif-drawable:1.2.6',
            "cymRecyclerViewAdapter"         : 'com.github.CymChad:BaseRecyclerViewAdapterHelper:2.9.30',
            "PhotoPicker"                    : 'me.iwf.photopicker:PhotoPicker:0.9.12@aar',
            "ucrop"                          : 'com.yalantis:ucrop:2.2.0',
            "WheelPicker"                    : 'cn.aigestudio.wheelpicker:WheelPicker:1.1.2',
            "Blankj-utilcode"                : 'com.blankj:utilcode:1.4.1',

            ////// ====== third-party ====== ////////

            "jcip-annotations"               : 'net.jcip:jcip-annotations:1.0',
            "growingio"                      : 'com.growingio.android:vds-android-agent:0.9.104@aar',
            "aliyun-oss"                     : 'com.aliyun.dpa:oss-android-sdk:2.3.0',
            "getui"                          : 'com.getui:sdk:2.12.1.0',
            ////// ====== local library ====== ////////

            "junit"                          : "junit:junit:4.12"
    ]

    dependencies_apt = [
            ////// ====== apt ====== ///////

            "databinding-compiler": "com.android.databinding:compiler:$androidcfg.pluginVersion",
            "lifecycle-compiler"  : "android.arch.lifecycle:compiler:${dependencies_ver['lifecycle_version']}",
    ]
}
