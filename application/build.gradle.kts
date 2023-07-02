val bootJar: org.springframework.boot.gradle.tasks.bundling.BootJar by tasks
bootJar.enabled = false

plugins {
  id("org.springframework.boot")
  id("io.spring.dependency-management")
  kotlin("plugin.spring")
  id("java-test-fixtures")
}

dependencies {

  api(project(":domain"))
  implementation(project(":port-in"))
  implementation(project(":port-out"))

  implementation("org.springframework.boot:spring-boot-starter-logging")
  implementation("org.springframework:spring-context")
  implementation("org.springframework:spring-tx")

  testImplementation("org.springframework.boot:spring-boot-starter-test")
  testImplementation(project(":port-in"))
  testImplementation(project(":port-out"))
  testImplementation(testFixtures(project(":domain")))
  testImplementation(testFixtures(project(":port-in")))
}
