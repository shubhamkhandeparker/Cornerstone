import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.shubham.cornerstone"

    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.shubham.cornerstone"
        minSdk = 24
        targetSdk = 36

        versionCode = 12
        versionName = "2.0"

        testInstrumentationRunner =
            "androidx.test.runner.AndroidJUnitRunner"

        val localProperties =
            Properties()

        val localFile =
            rootProject.file(
                "local.properties"
            )

        if (localFile.exists()) {
            localProperties.load(
                FileInputStream(
                    localFile
                )
            )
        }

        val groqKey =
            localProperties.getProperty(
                "GROQ_API_KEY"
            ) ?: ""

        buildConfigField(
            "String",
            "GROQ_API_KEY",
            "\"$groqKey\""
        )
    }

    buildTypes {
        release {
            isMinifyEnabled = false

            proguardFiles(
                getDefaultProguardFile(
                    "proguard-android-optimize.txt"
                ),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility =
            JavaVersion.VERSION_11

        targetCompatibility =
            JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {
    implementation(
        libs.androidx.core.ktx
    )

    implementation(
        libs.androidx.lifecycle.runtime.ktx
    )

    implementation(
        libs.androidx.activity.compose
    )

    implementation(
        platform(
            libs.androidx.compose.bom
        )
    )

    implementation(
        libs.androidx.compose.ui
    )

    implementation(
        libs.androidx.compose.ui.graphics
    )

    implementation(
        libs.androidx.compose.ui.tooling.preview
    )

    implementation(
        libs.androidx.compose.material3
    )

    // Room — local database
    implementation(
        libs.androidx.room.runtime
    )

    implementation(
        libs.androidx.room.ktx
    )

    ksp(
        libs.androidx.room.compiler
    )

    // Google Play Billing 9.1.0
    implementation(
        libs.billing.ktx
    )

    // ViewModel and navigation
    implementation(
        libs.androidx.lifecycle.viewmodel.compose
    )

    implementation(
        libs.androidx.navigation.compose
    )

    // DataStore
    implementation(
        libs.androidx.datastore.preferences
    )

    // Networking
    implementation(
        libs.retrofit
    )

    implementation(
        libs.retrofit.kotlinx.serialization
    )

    implementation(
        libs.okhttp.logging
    )

    implementation(
        libs.kotlinx.serialization.json
    )

    // Coil
    implementation(
        "io.coil-kt.coil3:coil-compose:3.0.4"
    )

    implementation(
        "io.coil-kt.coil3:coil-network-okhttp:3.0.4"
    )

    // CameraX
    val cameraxVersion =
        "1.6.1"

    implementation(
        "androidx.camera:camera-core:$cameraxVersion"
    )

    implementation(
        "androidx.camera:camera-camera2:$cameraxVersion"
    )

    implementation(
        "androidx.camera:camera-lifecycle:$cameraxVersion"
    )

    implementation(
        "androidx.camera:camera-view:$cameraxVersion"
    )

    testImplementation(
        libs.junit
    )

    androidTestImplementation(
        libs.androidx.junit
    )

    androidTestImplementation(
        libs.androidx.espresso.core
    )

    androidTestImplementation(
        platform(
            libs.androidx.compose.bom
        )
    )

    androidTestImplementation(
        libs.androidx.compose.ui.test.junit4
    )

    debugImplementation(
        libs.androidx.compose.ui.tooling
    )

    debugImplementation(
        libs.androidx.compose.ui.test.manifest
    )
}