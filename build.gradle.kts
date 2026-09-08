plugins {
    id("java")
    id("io.qameta.allure") version "4.1.0"
}

group = "com.yuri.ui.test"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.14.4"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    testImplementation("com.codeborne:selenide:7.18.0")
    testImplementation("com.codeborne:selenide-video-recorder:7.18.0")

    testImplementation("io.qameta.allure:allure-junit5:2.27.0")
    testImplementation("io.qameta.allure:allure-selenide:2.27.0")

    testImplementation("org.slf4j:slf4j-simple:2.0.18")

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
    systemProperty("allure.results.directory", layout.buildDirectory.dir("allure-results").get().asFile.absolutePath)
    systemProperty("webdriver.selenium.manager.avoidStats", "true")
}