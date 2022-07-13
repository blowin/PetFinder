package com.lefarmico.petfinder.utils

import com.lefarmico.petfinder.BuildConfig

class AppConfig {

    fun isDebug(): Boolean {
        return BuildConfig.DEBUG
    }
}
