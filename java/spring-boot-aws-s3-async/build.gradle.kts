plugins {
	java
	id("org.springframework.boot") version "3.0.6"
	id("io.spring.dependency-management") version "1.1.0"
}

group = "com.devlach"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

val awsSdkVersion = "2.20.56"
val awsSdkCrtVersion = "0.21.12"

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation(platform("software.amazon.awssdk:bom:$awsSdkVersion"))
	implementation("software.amazon.awssdk:s3")
	implementation("software.amazon.awssdk:s3-transfer-manager")
	implementation("software.amazon.awssdk.crt:aws-crt:$awsSdkCrtVersion")
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
