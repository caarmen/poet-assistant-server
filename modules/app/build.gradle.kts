plugins {
    alias(libs.plugins.spring)
    alias(libs.plugins.spring.boot) apply false
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
}
