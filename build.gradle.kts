import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.springframework.boot.gradle.plugin.SpringBootPlugin

plugins {
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.jvm)
    jacoco
}

group = "ca.rmen"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_21

repositories {
    mavenCentral()
}

dependencies {

    File("modules").listFiles()?.forEach {
        implementation(project(":modules:${it.name}"))
    }
    implementation(platform(SpringBootPlugin.BOM_COORDINATES))

    testImplementation("org.springframework.boot:spring-boot-starter-restdocs")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.boot:spring-boot-starter-webmvc-test")
    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
}
springBoot {
    mainClass.set("ca.rmen.poetassistant.PoetAssistantApplicationKt")
}
tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<JacocoReport> {
    classDirectories.setFrom(
        fileTree(rootProject.projectDir)
            .include("**/build/classes/kotlin/main/ca/rmen/poetassistant/**")
    )
    sourceDirectories.setFrom(files(File("${rootProject.projectDir}/modules").listFiles()?.map {
        File("$it/src/main/kotlin")
    }))
}

subprojects {
    repositories {
        mavenCentral()
    }
    apply(plugin = "org.jetbrains.kotlin.jvm")
    tasks.withType<KotlinCompile> {
        compilerOptions {
            freeCompilerArgs.add("-Xjsr305=strict")
            jvmTarget.set(JvmTarget.JVM_21)
            javaParameters.set(true)
        }
    }
    dependencies {
        "implementation"(platform(SpringBootPlugin.BOM_COORDINATES))
        // https://www.baeldung.com/spring-boot-actuators
        // https://docs.spring.io/spring-boot/api/rest/actuator/index.html
        implementation("org.springframework.boot:spring-boot-starter-actuator")
    }
}

kotlin {
    jvmToolchain(21)
}