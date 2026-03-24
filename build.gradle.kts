plugins {
    kotlin("jvm") version "2.3.0"
}

group = "org.pushups"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.11.0")
}

kotlin {
    jvmToolchain(25)
}

tasks.test {
    useJUnitPlatform()
}