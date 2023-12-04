import org.springframework.boot.gradle.tasks.bundling.BootJar

val jar: Jar by tasks
val bootJar: BootJar by tasks
bootJar.enabled = false
jar.enabled = true

plugins {
  id("org.springframework.boot")
  kotlin("plugin.spring")
  kotlin("plugin.jpa")
}

val postgresqlVersion: String by rootProject
dependencies {

  api(project(":port-out"))
  implementation(project(":domain"))

  implementation("org.springframework.data:spring-data-envers")
  implementation("org.springframework.boot:spring-boot-starter-web")
  implementation("org.springframework.boot:spring-boot-starter-data-jpa")
  implementation(group = "com.querydsl", name = "querydsl-jpa", classifier = "jakarta")
  kapt(group = "com.querydsl", name = "querydsl-apt", classifier = "jakarta")

  implementation("org.flywaydb:flyway-core")

  runtimeOnly("org.postgresql:postgresql:$postgresqlVersion")
  testImplementation("org.springframework.boot:spring-boot-starter-test")
  testImplementation("org.springframework.boot:spring-boot-testcontainers")
  testImplementation("org.testcontainers:junit-jupiter")
  testImplementation("org.testcontainers:postgresql")
}
