import org.springframework.boot.gradle.plugin.SpringBootPlugin

plugins {
    id("org.springframework.boot") version springBootVersion
    id("io.spring.dependency-management") version springDependencyManagemntVersion
    id("java")
    jacoco
}

group = "ca.rmen"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {

    File("modules").listFiles()?.forEach {
        implementation(project(":modules:${it.name}"))
    }

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
}
springBoot {
    mainClass.set("ca.rmen.poetassistant.PoetAssistantApplication")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<JacocoReport> {
    classDirectories.setFrom(
        fileTree(rootProject.projectDir)
            .include("**/build/classes/java/main/ca/rmen/poetassistant/**")
    )
    sourceDirectories.setFrom(files(File("${rootProject.projectDir}/modules").listFiles()?.map {
        File("$it/src/main/java")
    }))
}

subprojects {
    repositories {
        mavenCentral()
    }
    apply(plugin = "java-library")
    apply(plugin = "io.spring.dependency-management")
    dependencyManagement {
        imports {
            mavenBom(SpringBootPlugin.BOM_COORDINATES)
        }
    }
}

