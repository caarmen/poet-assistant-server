plugins {
    alias(libs.plugins.spring.boot) apply false
}

dependencies {

    api("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")

    implementation("org.hibernate.orm:hibernate-community-dialects")
    implementation(libs.sqlite.jdbc)
}
