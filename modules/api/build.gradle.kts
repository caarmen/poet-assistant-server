plugins {
    id("org.springframework.boot") apply false
    id("io.freefair.lombok") version lombokGradlePluginVersion
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation(project(":modules:service"))
}
