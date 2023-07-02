plugins {
    id("java-test-fixtures")
}

dependencies {
    implementation(project(":domain"))

    testFixturesImplementation(testFixtures(project(":domain")))
}
