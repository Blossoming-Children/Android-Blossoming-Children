// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.2.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false

    id("org.jlleitschuh.gradle.ktlint") version "10.2.1"
    id("com.diffplug.spotless") version "6.19.0"
    id("com.dropbox.dependency-guard") version "0.3.2"
}

dependencyGuard {
    configuration("classpath")
}

subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    apply(plugin = "com.diffplug.spotless")
    spotless {
        kotlin {
            target("src/**/*.kt")
            targetExclude("**/build/**/*.kt")
            ktlint("0.48.1")
        }
        format("kts") {
            target("src/**/*.kts")
            targetExclude("**/build/**/*.kts")
        }
    }
}
