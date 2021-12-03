plugins {
    id("org.springframework.boot") apply false
}

dependencies {

    api("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")

    implementation("com.github.gwenn:sqlite-dialect:$sqliteDialectVersion")
    implementation("org.xerial:sqlite-jdbc:$sqliteJdbcVersion")
}
