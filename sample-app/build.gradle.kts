plugins {
  id("org.springframework.boot")
  id("io.spring.dependency-management")
  kotlin("plugin.spring")
}

dependencies {
  implementation(project(":application"))
  implementation(project(":adapters:web-adapter"))
  implementation(project(":adapters:persistence-adapter"))

  implementation("org.springframework.boot:spring-boot-starter-actuator")
  runtimeOnly("io.micrometer:micrometer-registry-prometheus")

  testImplementation("org.springframework.boot:spring-boot-starter-test")
  testImplementation(testFixtures(project(":domain")))
}
