plugins {
    id("java")
}

group = "net.dbsgameplay.admintool"
version = "1.0-SNAPSHOT"


repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    compileOnly("io.papermc.paper:paper-api:1.20.2-R0.1-SNAPSHOT")

    // Add the DBsGameplayCore project as a dependency
    implementation(project(":DBsGameplayCore"))
}


tasks.test {
    useJUnitPlatform()
}