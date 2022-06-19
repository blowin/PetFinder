import com.lefarmico.buildsrc.*

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = ConfigVers.targetSdk

    defaultConfig {
        applicationId = "com.lefarmico.petfinder"
        minSdk = ConfigVers.minSdk
        targetSdk = ConfigVers.targetSdk
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
        kotlinCompilerExtensionVersion = ConfigVers.compose
    }
    packagingOptions {
        resources { excludes += "/META-INF/{AL2.0,LGPL2.1}" }
    }
}

dependencies {
    implementation(project(mapOf("path" to ":core")))

    //  --- compose ---
    implementation(Config.composeUi)
    implementation(Config.composeMaterial)
    implementation(Config.composeUiToolingPreview)
    implementation(Config.constraintCompose)
    implementation(Config.composeLiveData)
    implementation(Config.composeLifecycleViewModel)
    implementation(Config.composeNavigation)

    // --- core ---
    implementation(Config.core)
    implementation(Config.lifecycleRuntime)
    implementation(Config.activityCompose)
    implementation(Config.sharedPreferences)

    // --- testing ---
    testImplementation(Config.jUnit)
    androidTestImplementation(Config.jUnitExt)
    androidTestImplementation(Config.espresso)
    androidTestImplementation(Config.jUnitCompose)

    // --- debug ---
    debugImplementation(Config.composeUiTooling)
}
