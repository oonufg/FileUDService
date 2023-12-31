plugins {
    id("java-library")
    id("org.springframework.boot") version "3.1.4"
    id("io.spring.dependency-management") version "1.1.3"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    //Spring

    implementation("org.springframework.boot:spring-boot-starter-web:3.1.4")
    implementation("org.postgresql:postgresql:42.6.0")


}

tasks.jar{

}

tasks.test {
    useJUnitPlatform()
}