pluginManagement {
    repositories {
        maven {
            name = "Fabric"
            url = uri("https://maven.fabricmc.net/")
        }
        mavenCentral()
        gradlePluginPortal()
    }

    plugins {
        id("net.fabricmc.fabric-loom") version providers.gradleProperty("loom_version").get()
        id("com.diffplug.spotless") version providers.gradleProperty("spotless_version").get()
    }
}

rootProject.name = "directorscutworkstation"

include("common")
include("fabric-26-1-2")