package com.lefarmico.petfinder

import com.lefarmico.core.base.app.ApplicationConfigInitializer
import com.lefarmico.core.base.app.BaseApplication
import com.lefarmico.core.base.app.config.TimberConfig

class App : BaseApplication() {

    private val appConfigInitializer = ApplicationConfigInitializer(
        TimberConfig(isDebug)
    )

    private val isDebug get() = BuildConfig.DEBUG

    override fun onCreate() {
        super.onCreate()
        appConfigInitializer.init(this)
    }
}
