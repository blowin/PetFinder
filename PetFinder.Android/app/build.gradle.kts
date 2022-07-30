import com.google.protobuf.gradle.*
import com.lefarmico.buildsrc.*

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.parcelize")
    id("org.jetbrains.kotlin.kapt")
    id("dagger.hilt.android.plugin")
    id("com.google.protobuf")
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

protobuf {
    protoc { artifact = "com.google.protobuf:protoc:3.21.2" }
    plugins {
        id("java") { artifact = "io.grpc:protoc-gen-grpc-java:1.47.0" }
        id("grpc") { artifact = "io.grpc:protoc-gen-grpc-java:1.47.0" }
        id("grpckt") { artifact = "io.grpc:protoc-gen-grpc-kotlin:1.3.0:jdk8@jar" }
    }
    generateProtoTasks {
        all().forEach {
            it.plugins {
                id("java") {
                    option("lite")
                }
                id("grpc") {
                    option("lite")
                }
                id("grpckt") {
                    option("lite")
                }
            }
            it.builtins {
                id("kotlin") {
                    option("lite")
                }
            }
        }
    }
}

dependencies {
    implementation(project(mapOf("path" to ":core")))

    //  --- compose ---
    implementation(Config.Compose.UI)
    implementation(Config.Compose.MaterialYou)
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

    // --- images ---
    implementation(Config.Images.Landscapist_Glide)

    // --- grpc ---
    implementation("io.grpc:grpc-stub:1.46.0")
    implementation("io.grpc:grpc-protobuf-lite:1.47.0")
    implementation("io.grpc:grpc-kotlin-stub:1.3.0")
    implementation("com.google.protobuf:protobuf-kotlin-lite:3.21.2")
    implementation("io.grpc:grpc-okhttp:1.47.0")
    implementation("io.grpc:grpc-android:1.47.0")

    annotationProcessor("com.github.bumptech.glide:compiler:4.10.0")

    // --- OkHttp ---

    // --- javax ---
    implementation("javax.annotation:javax.annotation-api:1.3.2")
}
