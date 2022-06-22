package com.lefarmico.core.base.app.config

import com.lefarmico.core.base.app.ApplicationConfig
import com.lefarmico.core.base.app.BaseApplication
import timber.log.Timber

class TimberConfig(private val isDebug: Boolean) : ApplicationConfig {
    override fun init(application: BaseApplication) {
        if (isDebug) {
            Timber.plant(Timber.DebugTree())
        } else {
            // TODO: Firebase crashlytics
        }
    }
}
