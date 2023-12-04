import org.springframework.boot.gradle.tasks.bundling.BootJar

val bootJar: BootJar by tasks
bootJar.enabled = false

plugins {
  id("org.springframework.boot")
  kotlin("plugin.spring")
}

dependencies {
  api(project(":port-in"))
  implementation(project(":domain"))

  implementation("org.springframework.boot:spring-boot-starter-web")
  implementation("org.springframework.boot:spring-boot-starter-aop")
  implementation("org.springframework.boot:spring-boot-starter-validation")

  
  testImplementation("org.springframework.boot:spring-boot-starter-test")
  testImplementation(testFixtures(project(":domain")))
}
