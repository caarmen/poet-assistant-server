plugins {
    id("org.springframework.boot") apply false
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-json")

    implementation(project(":modules:model"))
    implementation(project(":modules:jpa"))
}
