rootProject.name = "Poet Assistant"
File("modules").listFiles()?.forEach {
    include(":modules:${it.name}")
}
