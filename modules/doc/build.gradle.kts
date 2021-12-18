import org.asciidoctor.gradle.jvm.AsciidoctorTask
plugins {
    id("org.springframework.boot") apply false
    id("org.asciidoctor.jvm.convert") version asciiDoctorVersion
    id("com.github.johnrengelman.processes") version johnrengelmanProcessesVerision
    id("org.springdoc.openapi-gradle-plugin") version openApiGradlePluginVersion
    id("org.hidetake.swagger.generator") version swaggerGeneratorVersion
}


val bootJar: org.springframework.boot.gradle.tasks.bundling.BootJar by tasks
bootJar.enabled = false

dependencies {
    implementation(project(":modules:api"))
    implementation(project(":modules:app"))
    implementation("org.springdoc:springdoc-openapi-ui:$springDocVersion")

    swaggerCodegen("io.swagger.codegen.v3:swagger-codegen-cli:$swaggerCodeGenVersion")
}

tasks {
    "asciidoctor"(AsciidoctorTask::class) {
        setOutputDir(file("${rootProject.projectDir}/docs"))
        setSourceDir(file("src/main/asciidoc"))
        attributes = mapOf("snippets" to file("${rootProject.buildDir}/snippets"))
    }
}

swaggerSources {
    create("poetassistant") {
        setInputFile(file("build/openapi.json"))
        code.language = "html"
        code.outputDir = file("${rootProject.projectDir}/docs/swagger")
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
