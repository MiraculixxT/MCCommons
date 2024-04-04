plugins {
    kotlin("jvm") version "1.9.23"
    kotlin("plugin.serialization") version "1.9.23"

    `java-library`
    `maven-publish`
    signing
}

group = "de.miraculixx"
version = "1.0.1"
val githubRepo = "Miraculixx/MCCommons"
val id = "mc-commons"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
//    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")

    val adventureVersion = "4.13.1"
    implementation("net.kyori:adventure-api:$adventureVersion")
    implementation("net.kyori:adventure-text-minimessage:$adventureVersion")
    implementation("net.kyori:adventure-text-serializer-plain:$adventureVersion")

    implementation("org.yaml:snakeyaml:2.2")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
    withSourcesJar()
    withJavadocJar()
}

tasks {
    compileJava {
        options.encoding = "UTF-8"
        options.release.set(17)
    }
    compileKotlin {
        kotlinOptions.jvmTarget = "17"
    }
}

signing {
    sign(publishing.publications)
}

publishing {
    repositories {
        maven {
            name = "ossrh"
            credentials(PasswordCredentials::class)
            setUrl("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2")
        }
    }

    publications {
        register<MavenPublication>(id) {
            from(components["java"])
//            artifact(tasks.jar.get().outputs.files.single())

            this.groupId = project.group.toString()
            this.artifactId = id
            this.version = project.version.toString()

            pom {
                name.set(id)
                description.set("Common utilities for Minecraft plugins")

                developers {
                    developer {
                        name.set("miraculixx")
                    }
                }

                licenses {
                    license {
                        name.set("GNU General Public License, Version 3")
                        url.set("https://www.gnu.org/licenses/gpl-3.0.en.html")
                    }
                }

                url.set("https://github.com/${githubRepo}")

                scm {
                    connection.set("scm:git:git://github.com/${githubRepo}.git")
                    url.set("https://github.com/${githubRepo}")
                }
            }
        }
    }
}
