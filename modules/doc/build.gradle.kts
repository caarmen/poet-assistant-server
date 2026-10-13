import org.asciidoctor.gradle.jvm.AsciidoctorTask
plugins {
    id("org.springframework.boot") apply false
    id("org.asciidoctor.jvm.convert") version asciiDoctorVersion
    id("org.springdoc.openapi-gradle-plugin") version openApiGradlePluginVersion
    id("org.hidetake.swagger.generator") version swaggerGeneratorVersion
    kotlin("plugin.spring")
}


val bootJar: org.springframework.boot.gradle.tasks.bundling.BootJar by tasks
bootJar.enabled = false

dependencies {
    implementation(project(":modules:api"))
    implementation(project(":modules:app"))
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:3.1.1")

    swaggerCodegen("io.swagger.codegen.v3:swagger-codegen-cli:$swaggerCodeGenVersion")
}

tasks {
	"asciidoctor"(AsciidoctorTask::class) {
		setOutputDir(file("${rootProject.projectDir}/docs"))
		sourceDir(file("src/main/asciidoc"))
		attributes = mapOf("snippets" to rootProject.layout.buildDirectory.dir("snippets").get().asFile)
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
	   description = "Generate docs"
	   dependsOn("asciidoctor")
	   dependsOn("generateSwaggerCode")
}

springBoot {
    mainClass.set("ca.rmen.poetassistant.PoetAssistantApplicationKt")
}

openApi {
    apiDocsUrl.set("http://localhost:8080/v3/api-docs")
    outputDir.set(layout.buildDirectory.dir("openapi"))
    outputFileName.set("openapi.json")
}

tasks.withType<org.springframework.boot.gradle.tasks.run.BootRun>().configureEach {
    val dbFile = rootProject.layout.projectDirectory
        .file("modules/app/src/main/resources/poet_assistant.db").asFile
    systemProperty("spring.datasource.url", "jdbc:sqlite:${dbFile.absolutePath}")
}
