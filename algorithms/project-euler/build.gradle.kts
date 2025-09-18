plugins {
    kotlin("jvm") version "2.2.20"
    application
}

group = "com.josimarsilva"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.13.4"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    testImplementation("org.assertj:assertj-core:3.27.4")
}

tasks.test {
    useJUnitPlatform()
}
