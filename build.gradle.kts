import org.asciidoctor.gradle.jvm.AsciidoctorTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.6.0"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.6.0"
    kotlin("plugin.spring") version "1.6.0"
    id("org.asciidoctor.jvm.convert") version "3.3.2"
    id("com.github.johnrengelman.processes") version "0.5.0"
    id("org.springdoc.openapi-gradle-plugin") version "1.3.3"
    id("org.hidetake.swagger.generator") version "2.18.2"
    jacoco
}

group = "ca.rmen"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

dependencies {
    val springDocVersion = "1.5.12"
    val sqliteDialectVersion = "0.1.2"
    val sqliteJdbcVersion = "3.36.0.3"
    val swaggerCodeGenVersion = "3.0.28"
    val jUnitVersion = "4.13.2"

    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-web")

    implementation("org.springdoc:springdoc-openapi-ui:$springDocVersion")
    implementation("org.springdoc:springdoc-openapi-kotlin:$springDocVersion")
    implementation("com.github.gwenn:sqlite-dialect:$sqliteDialectVersion")
    implementation("org.xerial:sqlite-jdbc:$sqliteJdbcVersion")

    testImplementation("junit:junit:$jUnitVersion")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")

    swaggerCodegen("io.swagger.codegen.v3:swagger-codegen-cli:$swaggerCodeGenVersion")
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

tasks {
    "asciidoctor"(AsciidoctorTask::class) {
        setOutputDir(file("docs"))
        setSourceDir(file("src/main/asciidoc"))
        attributes = mapOf("snippets" to file("build/snippets"))
    }
}

swaggerSources {
    create("poetassistant") {
        setInputFile(file("build/openapi.json"))
        code.language = "html"
        code.outputDir = file("$projectDir/docs/swagger")
    }
}

afterEvaluate {
    tasks.findByName("generateSwaggerCodePoetassistant")?.dependsOn("generateOpenApiDocs")
    tasks.findByName("asciidoctor")?.dependsOn("test")
}

tasks.register("generateDocs") {
    dependsOn("asciidoctor")
    dependsOn("generateSwaggerCode")
}
