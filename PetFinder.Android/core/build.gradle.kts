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
    implementation(Config.core)
    implementation(Config.appCompat)
    implementation(Config.lifecycleRuntime)
    implementation(Config.lifecycleViewModel)

    // ----- common -----
    implementation(Config.timber)

    // ----- testing ------
    testImplementation(Config.jUnit)
    androidTestImplementation(Config.jUnitExt)
    androidTestImplementation(Config.espresso)
    testImplementation(Config.coroutineTest)

    // ----- coroutine -----
    implementation(Config.coroutineCore)
    implementation(Config.coroutineAndroid)
}
