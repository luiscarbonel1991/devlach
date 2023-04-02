plugins {
    kotlin("jvm") version "1.8.0"
    id("com.google.protobuf") version "0.9.2"
    application
}

group = "com.devlach"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    google()
}

val grpcVersion = "1.54.0"
val grpcKotlinVersion = "1.3.0"
val protobufVersion = "3.22.2"
val annotationApiVersion = "6.0.53"

dependencies {
    testImplementation(kotlin("test"))
    implementation(kotlin("stdlib"))
    implementation("io.grpc:grpc-kotlin-stub:$grpcKotlinVersion")
    implementation("io.grpc:grpc-protobuf:$grpcVersion")
    implementation("com.google.protobuf:protobuf-kotlin:$protobufVersion")
    runtimeOnly("io.grpc:grpc-netty-shaded:$grpcVersion")
    if (JavaVersion.current().isJava9Compatible) {
        compileOnly("org.apache.tomcat:annotations-api:$annotationApiVersion") // necessary for Java 9+
    }
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:$protobufVersion"
    }

    plugins {
        create("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:$grpcVersion"
        }
        create("grpckt") {
            artifact = "io.grpc:protoc-gen-grpc-kotlin:$grpcKotlinVersion:jdk8@jar"
        }
    }

    generateProtoTasks {
        all().forEach {
            it.plugins {
                create("grpc")
                create("grpckt")
            }
            it.builtins {
                create("kotlin")
            }
        }
    }
}


tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17) // Depends on your JDK version
}

application {
    mainClass.set("ServerKt")
}


