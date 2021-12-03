plugins {
    id("org.springframework.boot") apply false
    scala
}

dependencies {

    api("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")

    implementation("org.scala-lang:scala3-library_3:$scala3Version")
    implementation("com.github.gwenn:sqlite-dialect:$sqliteDialectVersion")
    implementation("org.xerial:sqlite-jdbc:$sqliteJdbcVersion")
}
