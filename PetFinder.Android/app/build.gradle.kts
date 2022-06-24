import com.lefarmico.buildsrc.*

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.parcelize")
    id("org.jetbrains.kotlin.kapt")
    id("dagger.hilt.android.plugin")
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
//            signingConfig = signingConfigs.getByName("release")
            isMinifyEnabled = true
            isShrinkResources = true
            isDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        debug {
//            signingConfig = signingConfigs.getByName("debug")
            isTestCoverageEnabled = false
            isDebuggable = true
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
    implementation(Config.Compose.UI)
    implementation(Config.Compose.Material)
    implementation(Config.Compose.ToolingPreview)
    implementation(Config.Compose.ConstrainLayout)
    implementation(Config.Compose.LiveData)
    implementation(Config.Compose.LifecycleViewModel)

    // --- navigation ---
    implementation(Config.Navigation.ComposeNavigation)

    // --- core ---
    implementation(Config.Common.Core)
    implementation(Config.Common.LifecycleRuntime)
    implementation(Config.Common.ActivityCompose)
    implementation(Config.Common.SharedPreferences)

    // --- dagger-hilt ---
    implementation(Config.DaggerHilt.Android)
    kapt(Config.DaggerHilt.Compiler)
    implementation(Config.DaggerHilt.Compose)

    // --- testing ---
    testImplementation(Config.Testing.jUnit)
    androidTestImplementation(Config.Testing.jUnitExt)
    androidTestImplementation(Config.Testing.Espresso)
    androidTestImplementation(Config.Testing.jUnitCompose)

    // --- debug ---
    debugImplementation(Config.Compose.Tooling)
}
