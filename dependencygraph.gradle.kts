import com.vanniktech.dependency.graph.generator.DependencyGraphGeneratorExtension
import com.vanniktech.dependency.graph.generator.DependencyGraphGeneratorPlugin

buildscript {
    dependencies {
        classpath("com.vanniktech:gradle-dependency-graph-generator-plugin:$dependencyGraphGenerator")
    }
    repositories {
        mavenCentral()
    }
}
repositories {
    mavenCentral()
}

// https://github.com/vanniktech/gradle-dependency-graph-generator-plugin
rootProject.plugins.apply(DependencyGraphGeneratorPlugin::class.java)
rootProject.configure<DependencyGraphGeneratorExtension> {
    generators.create("poetAssistant") {
        include = { dependency ->
            dependency.moduleGroup.startsWith("Poet Assistant")
        }
        children = { true }
    }
}
