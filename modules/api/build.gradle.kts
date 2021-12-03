plugins {
    id("org.springframework.boot") apply false
    kotlin("plugin.spring")
    scala
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.scala-lang:scala3-library_3:$scala3Version")
    implementation("com.fasterxml.jackson.module:jackson-module-scala_3:$scalaJsonVersion")
    implementation("com.github.pjfanning:jackson-module-scala3-enum_3:$scalaJsonEnumVersion")
    implementation(project(":modules:model"))
    implementation(project(":modules:service"))
}
