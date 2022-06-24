package com.lefarmico.petfinder

import com.lefarmico.core.base.app.ApplicationConfigInitializer
import com.lefarmico.core.base.app.BaseApplication
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class App : BaseApplication() {

    @Inject
    lateinit var appConfigInitializer: ApplicationConfigInitializer

    override fun onCreate() {
        super.onCreate()
        appConfigInitializer.init(this)
    }
}
