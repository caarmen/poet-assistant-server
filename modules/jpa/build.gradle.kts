plugins {
    id("org.springframework.boot") apply false
}

dependencies {
    val sqliteDialectVersion = "0.1.2"
    val sqliteJdbcVersion = "3.36.0.3"

    api("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")

    implementation("com.github.gwenn:sqlite-dialect:$sqliteDialectVersion")
    implementation("org.xerial:sqlite-jdbc:$sqliteJdbcVersion")

}
