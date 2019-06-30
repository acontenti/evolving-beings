plugins {
    application
    kotlin("jvm") version "1.3.21"
}

application {
    mainClassName = "MainKt"
}

dependencies {
    compile(kotlin("stdlib"))
    compile("com.kyonifer", "koma-core-ejml", "0.12")
    compile("com.kyonifer", "koma-plotting", "0.12")
}

repositories {
    maven("http://dl.bintray.com/kyonifer/maven")
    jcenter()
}