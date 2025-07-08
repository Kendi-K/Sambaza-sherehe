// Top-level build file where you can add configuration options common to all sub-projects/modules.
/*plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false

}*/
val roomVersion = "2.6.1"
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    /*id("com.android.application")
    id("org.jetbrains.kotlin.android")*/
    alias(libs.plugins.kotlin.kapt) apply false
    id("com.google.gms.google-services") version "4.4.2" apply false

}
buildscript {
    dependencies {
        // Add this line
        classpath("com.google.gms:google-services:4.3.10")
    }
}
