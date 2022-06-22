package com.lefarmico.buildsrc

object Config {

    //  --- compose ---
    const val composeUi = "androidx.compose.ui:ui:${ConfigVers.compose}"
    const val composeMaterial = "androidx.compose.material:material:${ConfigVers.compose}"
    const val composeUiToolingPreview =
        "androidx.compose.ui:ui-tooling-preview:${ConfigVers.compose}"
    const val composeUiTooling = "androidx.compose.ui:ui-tooling:${ConfigVers.compose}"
    const val constraintCompose = "androidx.constraintlayout:constraintlayout-compose:${ConfigVers.constraint_compose}"
    const val composeLiveData = "androidx.compose.runtime:runtime-livedata:${ConfigVers.compose}"
    const val composeLifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-compose:${ConfigVers.lifecycle_runtime}"
    const val composeNavigation = "androidx.navigation:navigation-compose:${ConfigVers.compose_navigation}"

    //  --- core ---
    const val core = "androidx.core:core-ktx:${ConfigVers.core}"
    const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${ConfigVers.lifecycle_runtime}"
    const val activityCompose = "androidx.activity:activity-compose:${ConfigVers.activity_compose}"
    const val sharedPreferences = "androidx.preference:preference-ktx:${ConfigVers.shared_preferences}"
    const val appCompat = "androidx.appcompat:appcompat:${ConfigVers.appcompat}"
    const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${ConfigVers.lifecycle_runtime}"

    // --- testing ---
    const val jUnit = "androidx.test.ext:junit:${ConfigVers.jUnit}"
    const val jUnitExt = "androidx.test.ext:junit:${ConfigVers.jUnit}"
    const val jUnitCompose = "androidx.compose.ui:ui-test-junit4:${ConfigVers.compose}"
    const val espresso = "androidx.test.espresso:espresso-core:${ConfigVers.espresso}"

    // --- common ---
    const val timber = "com.jakewharton.timber:timber:${ConfigVers.timber}"

    // --- coroutine ---
    const val coroutineCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${ConfigVers.coroutine}"
    const val coroutineAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${ConfigVers.coroutine}"
    const val coroutineTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${ConfigVers.coroutine}"
}
