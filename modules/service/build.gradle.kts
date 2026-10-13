plugins {
    alias(libs.plugins.spring.boot) apply false
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-json")

    implementation(project(":modules:repository"))
}
