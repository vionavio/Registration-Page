@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.kotlinParcelable)
    alias(libs.plugins.secretsGradlePlugin)
    kotlin("kapt") version "1.6.10"
}

android {
    namespace = "com.viona.registrationapp.core"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        buildConfig = true
    }
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.retrofit)
    implementation(libs.retrofitConverter)
    implementation(libs.kotlinCoroutines)
    implementation(libs.gson)
    implementation(libs.logging)
    implementation(libs.dagger)
    kapt(libs.daggerCompiler)
}