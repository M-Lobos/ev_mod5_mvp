plugins {
    alias(libs.plugins.android.application)

    id("kotlin-parcelize")

}

android {
    namespace = "com.lobosmanuel.ev_mod5_mvp"
    compileSdk {
        version = release(36) {
            minorApiLevel = 1
        }
    }

    defaultConfig {
        applicationId = "com.lobosmanuel.ev_mod5_mvp"
        minSdk = 25
        targetSdk = 36
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
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.filament.android)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    //implementación de Glide

    implementation("com.github.bumptech.glide:glide:4.16.0")
    // Si usas Kotlin, es mejor usar 'kapt' o 'annotationProcessor' con el grupo correcto
    annotationProcessor("com.github.bumptech.glide:compiler:4.16.0")
    //uso de GSON
    implementation("com.google.code.gson:gson:2.10.1")
}