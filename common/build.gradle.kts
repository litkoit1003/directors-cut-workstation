plugins {
    `java-library`
}

val lwjglVersion = "3.4.2"

dependencies {
    compileOnly("org.slf4j:slf4j-api:2.0.16")
    compileOnly("org.lwjgl:lwjgl:${lwjglVersion}")
    compileOnly("org.lwjgl:lwjgl-glfw:${lwjglVersion}")
    compileOnly("org.lwjgl:lwjgl-opengl:${lwjglVersion}")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(25))
    }

    withSourcesJar()
}

tasks.withType<JavaCompile>().configureEach {
    options.release.set(25)
    options.encoding = "UTF-8"
}