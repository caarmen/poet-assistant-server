plugins {
    alias(libs.plugins.spring)
    alias(libs.plugins.spring.boot) apply false
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation(project(":modules:service"))
}
