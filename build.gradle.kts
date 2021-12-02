import com.vanniktech.dependency.graph.generator.DependencyGraphGeneratorExtension
import com.vanniktech.dependency.graph.generator.DependencyGraphGeneratorPlugin
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.plugin.SpringBootPlugin

buildscript {
    dependencies {
        classpath("com.vanniktech:gradle-dependency-graph-generator-plugin:0.6.0")
    }
}
plugins {
    id("org.springframework.boot") version "2.6.0"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.6.0"
    kotlin("plugin.spring") version "1.6.0"
    jacoco
}

apply(plugin = "com.vanniktech.dependency.graph.generator")
group = "ca.rmen"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

dependencies {
    val jUnitVersion = "4.13.2"

    File("modules").listFiles()?.forEach {
        implementation(project(":modules:${it.name}"))
    }

    testImplementation("junit:junit:$jUnitVersion")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
}
springBoot {
    mainClass.set("ca.rmen.poetassistant.PoetAssistantApplicationKt")
}
tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

subprojects {
    repositories {
        mavenCentral()
    }
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "io.spring.dependency-management")
    dependencyManagement {
        imports {
            mavenBom(SpringBootPlugin.BOM_COORDINATES)
        }
    }
}

rootProject.plugins.apply(DependencyGraphGeneratorPlugin::class.java)
rootProject.configure<DependencyGraphGeneratorExtension> {
    generators.create("poetAssistant") {
        include = { dependency ->
            dependency.moduleGroup.startsWith("Poet Assistant")
        }
        children = { true }
    }
}
