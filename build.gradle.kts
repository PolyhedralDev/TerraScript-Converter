plugins {
    java
}

group = "com.dfsek"
version = "0.1.0"

repositories {
    mavenCentral()
}

dependencies {
    testCompile("junit", "junit", "4.12")
}

val jar by tasks.getting(Jar::class) {
    manifest {
        attributes["Main-Class"] = "com.dfsek.converter.ScriptConverter"
    }
}