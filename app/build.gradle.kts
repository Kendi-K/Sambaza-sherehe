val roomVersion = "2.6.1" // You have this defined at the top

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("org.jetbrains.kotlin.kapt") // Keep this for Room
    id("com.google.gms.google-services")
}

android {
    namespace = "com.strathmore.sambazasherehe"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.strathmore.sambazasherehe"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    // Compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.activity.compose) // For ComponentActivity.setContent

    // Lifecycle & ViewModel (Use the versions from your libs.versions.toml)
    implementation(libs.androidx.lifecycle.runtime.ktx.v286) // Or libs.androidx.lifecycle.runtime.ktx if v286 is the one you intend to use primarily
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    // Room (Use the roomVersion variable consistently)
    implementation("androidx.room:room-runtime:$roomVersion")
    kapt("androidx.room:room-compiler:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion") // For Coroutines support

    // Navigation
    implementation("androidx.navigation:navigation-compose:2.7.7") // You had a jvmstubs alias, but this is the typical dependency

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1")

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom)) // For Compose testing
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation ("com.google.firebase:firebase-database-ktx:20.0.3")
    implementation ("com.google.firebase:firebase-auth-ktx:21.0.1")
    // REMOVE these as they are likely duplicates or superseded by the above:
    // implementation(libs.androidx.lifecycle.runtime.ktx) // You have v286 or the other alias
    // implementation(libs.androidx.room.common.jvm) // room-runtime includes common
    // implementation(libs.androidx.room.runtime.android) // room-runtime is the main one
    // implementation(libs.androidx.navigation.compose.jvmstubs) // Use the direct navigation-compose
    // implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.0") // Covered by alias
    // implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.0") // Covered by alias
    // implementation("androidx.room:room-ktx:$roomVersion") // Already have this
}

/*val roomVersion = "2.6.1"
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    /*id("com.android.application")
    id("org.jetbrains.kotlin.android")*/
    id("org.jetbrains.kotlin.kapt")

}

android {
    namespace = "com.strathmore.sambazasherehe"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.strathmore.sambazasherehe"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.room.common.jvm)
    implementation(libs.androidx.room.runtime.android)
    implementation(libs.androidx.navigation.compose.jvmstubs)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.lifecycle.runtime.ktx.v286)
    implementation("androidx.room:room-runtime:$roomVersion")
    kapt("androidx.room:room-compiler:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.0")
    implementation("androidx.navigation:navigation-compose:2.7.7")
    implementation("androidx.room:room-ktx:$roomVersion")


}*/
