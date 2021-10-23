import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension
import org.gradle.kotlin.dsl.version
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.sql.DriverManager.println

buildscript {
    val springBootVersion = "2.3.4.RELEASE"
    val dependencyManagementVersion = "1.0.11.RELEASE"
    project.extra.set("springBootVersion", springBootVersion)

    dependencies {
        classpath("org.springframework.boot:spring-boot-gradle-plugin:$springBootVersion")
        classpath("io.spring.gradle:dependency-management-plugin:$dependencyManagementVersion")
    }
}

plugins {
    id("org.springframework.boot") version "2.5.6"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.4.20"
    kotlin("plugin.spring") version "1.4.20"
}

//java.sourceCompatibility = JavaVersion.VERSION_1_8

allprojects {
    group = "com.okila"
    version = "0.0.1"
    repositories {
        jcenter()
        mavenCentral()
    }
}

subprojects {
    apply {
        plugin("org.jetbrains.kotlin.jvm")
        plugin("kotlin")
        plugin("kotlin-spring")
        plugin("org.jetbrains.kotlin.kapt")
        plugin("org.springframework.boot")
        plugin("org.jetbrains.kotlin.plugin.spring")
        plugin("io.spring.dependency-management")
    }
    println("Enabling Kotlin Spring plugin in project ${project.name}...")

    the<DependencyManagementExtension>().apply {
        imports {
            mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
        }
    }
    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = "1.8"
            freeCompilerArgs = listOf("-Xjsr305=strict")
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
    tasks.withType<Jar> {
        enabled = true
    }

    dependencies {
        "api"(kotlin("stdlib-jdk8"))
        "api"(kotlin("reflect"))
        "api"("org.springframework.boot:spring-boot-starter")
        "api"("org.jetbrains.kotlin:kotlin-reflect")
        "api"("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
        "api"("org.springframework.boot:spring-boot-devtools")
        "testImplementation"("org.springframework.boot:spring-boot-starter-test") {
            exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
        }
    }

    configure<DependencyManagementExtension> {
        imports {
            val springBootVersion = parent?.extra?.get("springBootVersion")
            val springCloudVersion = "Hoxton.SR9"
            mavenBom("org.springframework.cloud:spring-cloud-dependencies:$springCloudVersion")
            mavenBom("org.springframework.boot:spring-boot-parent:$springBootVersion")
        }
    }
}