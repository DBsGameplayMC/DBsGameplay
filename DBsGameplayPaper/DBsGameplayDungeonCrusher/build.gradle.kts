plugins {
    id("java")
}

group = "net.dbsgameplay.dungeoncrusher"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    // Add the DBsGameplayPaperCore project as a dependency
    implementation(project(":DBsGameplayPaperCore"))
}

tasks.test {
    useJUnitPlatform()
}