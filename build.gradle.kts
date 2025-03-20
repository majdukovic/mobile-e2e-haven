
plugins {
    kotlin("jvm") version "1.9.0"
    application
}

group = "com.e2ehaven"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Kotlin
    implementation(kotlin("stdlib"))
    
    // Appium
    implementation("io.appium:java-client:8.5.1")
    
    // Selenium
    implementation("org.seleniumhq.selenium:selenium-java:4.11.0")
    
    // TestNG
    implementation("org.testng:testng:7.8.0")
    
    // Logging
    implementation("org.slf4j:slf4j-api:2.0.7")
    implementation("ch.qos.logback:logback-classic:1.4.8")
    
    // JSON
    implementation("com.google.code.gson:gson:2.10.1")
    
    // Apache Commons
    implementation("org.apache.commons:commons-lang3:3.12.0")
    implementation("commons-io:commons-io:2.13.0")
    
    // Reporting
    implementation("com.aventstack:extentreports:5.0.9")
    
    // AssertJ for more readable assertions
    implementation("org.assertj:assertj-core:3.24.2")
    
    // Testing
    testImplementation("org.mockito:mockito-core:5.4.0")
    testImplementation("org.mockito.kotlin:mockito-kotlin:5.0.0")
}

tasks.test {
    useTestNG {
        suites("src/test/resources/testng.xml")
    }
}

application {
    mainClass.set("com.e2ehaven.MainKt")
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "com.e2ehaven.MainKt"
    }
    
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    
    from(sourceSets.main.get().output)
    
    dependsOn(configurations.runtimeClasspath)
    from({
        configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) }
    })
}
