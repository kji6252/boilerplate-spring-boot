import org.jetbrains.kotlin.konan.properties.loadProperties

plugins {
    kotlin("jvm")
    kotlin("kapt")

    id("org.jlleitschuh.gradle.ktlint")
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}


allprojects {
    apply(plugin = "kotlin")
    apply(plugin = "kotlin-kapt")

    group = "io.github.kji6252"

    tasks.getByName<Test>("test") {
        useJUnitPlatform {
            excludeTags = setOf("integration")
        }
    }
    tasks.register<Test>("integrationTest") {
        useJUnitPlatform {
            includeTags = setOf("integration")
        }
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "17"
        }
    }
    kapt {
        correctErrorTypes = true
    }

    repositories {
        mavenCentral()
    }
}

val kotestVersion: String by rootProject
val mockkVersion: String by rootProject
val kotestExtensionsSpringVersion: String by rootProject
val mockitoVersion: String by rootProject
val kotlinCoroutinesVersion: String by rootProject
val mapstructVersion: String by rootProject

subprojects {
    dependencies {
        implementation(kotlin("stdlib"))
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
//        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

        implementation("org.mapstruct:mapstruct:$mapstructVersion")
        kapt("org.mapstruct:mapstruct-processor:$mapstructVersion")
        kaptTest("org.mapstruct:mapstruct-processor:$mapstructVersion")

        testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
        testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
        testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$kotlinCoroutinesVersion")
        testImplementation("io.mockk:mockk:$mockkVersion")
        testImplementation("io.kotest.extensions:kotest-extensions-spring:$kotestExtensionsSpringVersion")
        testImplementation("org.mockito.kotlin:mockito-kotlin:$mockitoVersion")
    }
    repositories {
        mavenCentral()
    }
}
