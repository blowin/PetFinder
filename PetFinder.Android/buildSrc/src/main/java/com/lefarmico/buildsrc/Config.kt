package com.lefarmico.buildsrc

object Config {

    object Compose {
        const val UI = "androidx.compose.ui:ui:${ConfigVers.compose}"
        const val Material = "androidx.compose.material:material:${ConfigVers.compose}"
        const val MaterialYou = "androidx.compose.material3:material3:${Versions.MaterialYou}"
        const val ToolingPreview =
            "androidx.compose.ui:ui-tooling-preview:${ConfigVers.compose}"
        const val Tooling = "androidx.compose.ui:ui-tooling:${ConfigVers.compose}"
        const val ConstrainLayout = "androidx.constraintlayout:constraintlayout-compose:${ConfigVers.constraint_compose}"
        const val LiveData = "androidx.compose.runtime:runtime-livedata:${ConfigVers.compose}"
        const val LifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-compose:${ConfigVers.lifecycle_runtime}"

        object Versions {
            const val MaterialYou = "1.0.0-alpha13"
        }
    }

    object Navigation {
        const val ComposeNavigation = "androidx.navigation:navigation-compose:${ConfigVers.compose_navigation}"
    }

    object Common {
        const val Core = "androidx.core:core-ktx:${ConfigVers.core}"
        const val LifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${ConfigVers.lifecycle_runtime}"
        const val ActivityCompose = "androidx.activity:activity-compose:${ConfigVers.activity_compose}"
        const val SharedPreferences = "androidx.preference:preference-ktx:${ConfigVers.shared_preferences}"
        const val AppCompat = "androidx.appcompat:appcompat:${ConfigVers.appcompat}"
        const val LifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${ConfigVers.lifecycle_runtime}"

        const val Timber = "com.jakewharton.timber:timber:${ConfigVers.timber}"
    }

    object Testing {
        const val jUnit = "androidx.test.ext:junit:${ConfigVers.jUnit}"
        const val jUnitExt = "androidx.test.ext:junit:${ConfigVers.jUnit}"
        const val jUnitCompose = "androidx.compose.ui:ui-test-junit4:${ConfigVers.compose}"
        const val Espresso = "androidx.test.espresso:espresso-core:${ConfigVers.espresso}"
    }

    object DaggerHilt {
        const val Android = "com.google.dagger:hilt-android:${Versions.Android}"
        const val Compiler = "com.google.dagger:hilt-android-compiler:${Versions.Compiler}"
        const val Compose = "androidx.hilt:hilt-navigation-compose:${Versions.Compose}"

        object Versions {
            const val Android = "2.41"
            const val Compiler = "2.41"
            const val Compose = "1.0.0"
        }
    }

    object Coroutine {
        const val Core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${ConfigVers.coroutine}"
        const val Android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${ConfigVers.coroutine}"
        const val Test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${ConfigVers.coroutine}"
    }

    object FirebaseLib {
        const val Base = "com.google.android.gms:play-services-base:${Version.Base}"
        const val Analytic = "com.google.firebase:firebase-analytics-ktx:${Version.Analytic}"
        const val Crashlytics = "com.google.firebase:firebase-crashlytics-ktx:${Version.Crashlytics}"
        const val Push = "com.google.firebase:firebase-messaging-ktx:${Version.Push}"
        const val Config = "com.google.firebase:firebase-config-ktx:${Version.Config}"

        object Version {
            const val Base = "18.0.1"
            const val Analytic = "20.1.2"
            const val Crashlytics = "18.2.9"
            const val Push = "23.0.3"
            const val Config = "21.0.2"
        }
    }

    object Images {
        const val Landscapist_Glide = "com.github.skydoves:landscapist-glide:${Version.Landscapist_Glide}"

        object Version {
            const val Landscapist_Glide = "1.5.3"
        }
    }
}

object ConfigVers {

    // --- compose ---
    const val compose = "1.2.0-beta01"
    const val constraint_compose = "1.0.1"
    const val compose_navigation = "2.4.1"

    // --- core ---
    const val core = "1.7.0"
    const val activity_compose = "1.3.1"
    const val lifecycle_runtime = "2.4.1"
    const val shared_preferences = "1.2.0"
    const val appcompat = "1.4.2"

    // --- testing ---
    const val jUnit = "1.1.3"
    const val espresso = "3.4.0"

    // --- androidSdk ---
    const val minSdk = 26
    const val targetSdk = 32
    const val compileSdk = 32

    // --- common ---
    const val timber = "5.0.1"

    // --- coroutine ---
    const val coroutine = "1.6.1"
}
