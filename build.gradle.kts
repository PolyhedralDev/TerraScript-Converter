plugins {
    java
}

group = "com.dfsek"
version = "0.1.0"

repositories {
    mavenCentral()
    maven { url = uri("https://repo.codemc.org/repository/maven-public") }
    maven { url = uri("https://jitpack.io/") }
}

dependencies {
    testCompile("junit", "junit", "4.12")
    implementation("com.dfsek:Tectonic:1.2.3")
    implementation("org.yaml:snakeyaml:1.27")
    implementation("commons-io:commons-io:2.8.0")
    implementation("com.github.Querz:NBT:5.2")
}

val jar by tasks.getting(Jar::class) {
    manifest {
        attributes["Main-Class"] = "com.dfsek.converter.ScriptConverter"
    }
}