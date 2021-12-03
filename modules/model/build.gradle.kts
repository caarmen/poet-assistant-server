plugins {
    id("org.springframework.boot") apply false
    scala
}

dependencies {
    implementation("org.scala-lang:scala3-library_3:$scala3Version")
    implementation("org.springframework.boot:spring-boot-starter-json")
}
