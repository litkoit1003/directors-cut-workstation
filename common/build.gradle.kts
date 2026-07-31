plugins {
    `java-library`
}

dependencies {
    compileOnly("org.slf4j:slf4j-api:2.0.16")
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