plugins {
    kotlin("jvm") version "1.9.0"
    application
}

group = "com.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("jakarta.xml.bind:jakarta.xml.bind-api:4.0.0")
    implementation("com.sun.xml.bind:jaxb-impl:4.0.0")
    implementation("jakarta.activation:jakarta.activation-api:2.1.0")
}

kotlin {
    jvmToolchain(11)
}

application {
    mainClass.set("MainKt")
}
