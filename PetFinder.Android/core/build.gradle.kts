import com.lefarmico.buildsrc.*

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = ConfigVers.compileSdk

    defaultConfig {
        minSdk = ConfigVers.minSdk
        targetSdk = ConfigVers.targetSdk

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
}

dependencies {

    // ----- core -----
    implementation(Config.Common.Core)
    implementation(Config.Common.AppCompat)
    implementation(Config.Common.LifecycleRuntime)
    implementation(Config.Common.LifecycleViewModel)

    // ----- common -----
    implementation(Config.Common.Timber)

    // ----- testing ------
    testImplementation(Config.Testing.jUnit)
    androidTestImplementation(Config.Testing.jUnitExt)
    androidTestImplementation(Config.Testing.Espresso)
    testImplementation(Config.Coroutine.Test)

    // ----- coroutine -----
    implementation(Config.Coroutine.Core)
    implementation(Config.Coroutine.Android)
}
