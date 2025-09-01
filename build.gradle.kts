// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false

    id("com.google.gms.google-services") version "4.4.3" apply false

    id("com.google.devtools.ksp") version "2.0.21-1.0.27" apply false

    kotlin("jvm") version "2.2.0"
    kotlin("plugin.serialization") version "2.2.0"
}

buildscript{
    repositories{
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.56.2")
    }

}



