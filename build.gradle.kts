import org.jetbrains.kotlin.config.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

repositories {
  mavenCentral()
  maven("https://jitpack.io")
  maven("https://repo.spring.io/milestone")
  maven("https://repo.spring.io/snapshot")
}
plugins {
  id("org.springframework.boot") version "3.2.0-SNAPSHOT"
  id("io.spring.dependency-management") version "1.1.2"
  kotlin("jvm") version "1.9.0"
  kotlin("plugin.spring") version "1.9.0"
}
dependencies {
  implementation("org.springframework.boot:spring-boot-starter-web")
  implementation("org.jetbrains.kotlin:kotlin-reflect")
  implementation("io.github.kotlin-telegram-bot.kotlin-telegram-bot:telegram:6.1.0")
  testImplementation("org.springframework.boot:spring-boot-starter-test")
  testImplementation("com.google.truth:truth:1.1.3")
  testImplementation("io.mockk:mockk:1.13.5")
}
tasks.withType<KotlinCompile> {
  kotlinOptions.jvmTarget = "19"
  kotlinOptions.freeCompilerArgs += listOf("-opt-in=kotlin.time.ExperimentalTime", "-Xjsr305=strict")
}
tasks.withType<JavaCompile> {
  sourceCompatibility = "19"
  targetCompatibility = "19"
}
tasks.test {
  useJUnitPlatform()
  jvmArgs("--enable-preview")
}
tasks.bootJar {
  archiveVersion.set("boot")
}
