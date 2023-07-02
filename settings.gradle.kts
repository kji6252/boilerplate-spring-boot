rootProject.name = "boilerplate-spring-boot"

pluginManagement {
    resolutionStrategy {
        eachPlugin {
            when (requested.id.id) {
                "org.jetbrains.kotlin.plugin.allopen" -> {
                    val kotlinVersion: String by settings
                    useVersion(kotlinVersion)
                }

                "org.jetbrains.kotlin.jvm" -> {
                    val kotlinVersion: String by settings
                    useVersion(kotlinVersion)
                }

                "org.jetbrains.kotlin.kapt" -> {
                    val kotlinVersion: String by settings
                    useVersion(kotlinVersion)
                }

                "org.jetbrains.kotlin.plugin.jpa" -> {
                    val kotlinVersion: String by settings
                    useVersion(kotlinVersion)
                }

                "org.jetbrains.kotlin.plugin.spring" -> {
                    val kotlinVersion: String by settings
                    useVersion(kotlinVersion)
                }

                "org.springframework.boot" -> {
                    val springBootVersion: String by settings
                    useVersion(springBootVersion)
                }

                "io.spring.dependency-management" -> {
                    val springDependencyManagementVersion: String by settings
                    useVersion(springDependencyManagementVersion)
                }

                "org.jlleitschuh.gradle.ktlint" -> {
                    val ktlintVersion: String by settings
                    useVersion(ktlintVersion)
                }
            }
        }
    }
}

include("domain")
include("application")
include("port-in", "port-out")
include("sample-app")
include("adapters:web-adapter")
findProject(":adapters:web-adapter")?.name = "web-adapter"