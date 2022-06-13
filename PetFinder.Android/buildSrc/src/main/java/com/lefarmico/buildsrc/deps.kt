package com.lefarmico.buildsrc

object deps {

    //  --- compose ---
    const val composeUi = "androidx.compose.ui:ui:${versions.compose}"
    const val composeMaterial = "androidx.compose.material:material:${versions.compose}"
    const val composeUiToolingPreview =
        "androidx.compose.ui:ui-tooling-preview:${versions.compose}"
    const val composeUiTooling = "androidx.compose.ui:ui-tooling:${versions.compose}"
    const val constraintCompose = "androidx.constraintlayout:constraintlayout-compose:${versions.constraint_compose}"
    const val composeLiveData = "androidx.compose.runtime:runtime-livedata:${versions.compose}"
    const val composeLifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-compose:${versions.lifecycle_runtime}"
    const val composeNavigation = "androidx.navigation:navigation-compose:${versions.compose_navigation}"

    //  --- core ---
    const val core = "androidx.core:core-ktx:${versions.core}"
    const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${versions.lifecycle_runtime}"
    const val activityCompose = "androidx.activity:activity-compose:${versions.activity_compose}"
    const val sharedPreferences = "androidx.preference:preference-ktx:${versions.shared_preferences}"

    // --- testing ---
    const val jUnit = "androidx.test.ext:junit:${versions.jUnit}"
    const val jUnitExt = "androidx.test.ext:junit:${versions.jUnit}"
    const val jUnitCompose = "androidx.compose.ui:ui-test-junit4:${versions.compose}"
    const val espresso = "androidx.test.espresso:espresso-core:${versions.espresso}"
}
