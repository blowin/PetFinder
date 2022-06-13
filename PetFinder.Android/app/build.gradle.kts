import com.lefarmico.buildsrc.*

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = versions.targetSdk

    defaultConfig {
        applicationId = "com.lefarmico.petfinder"
        minSdk = versions.minSdk
        targetSdk = versions.targetSdk
        versionCode = 1
        versionName = "0.0.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables { useSupportLibrary = true }
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
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = versions.compose
    }
    packagingOptions {
        resources { excludes += "/META-INF/{AL2.0,LGPL2.1}" }
    }
}

dependencies {

    //  --- compose ---
    implementation(deps.composeUi)
    implementation(deps.composeMaterial)
    implementation(deps.composeUiToolingPreview)
    implementation(deps.constraintCompose)
    implementation(deps.composeLiveData)
    implementation(deps.composeLifecycleViewModel)
    implementation(deps.composeNavigation)

    // --- core ---
    implementation(deps.core)
    implementation(deps.lifecycleRuntime)
    implementation(deps.activityCompose)
    implementation(deps.sharedPreferences)

    // --- testing ---
    testImplementation(deps.jUnit)
    androidTestImplementation(deps.jUnitExt)
    androidTestImplementation(deps.espresso)
    androidTestImplementation(deps.jUnitCompose)

    // --- debug ---
    debugImplementation(deps.composeUiTooling)
}
