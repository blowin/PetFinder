package com.lefarmico.core.base.app

import android.app.Application
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.lefarmico.core.base.app.activity.ActivityLifecycleCallback

abstract class BaseApplication : Application(), LifecycleEventObserver {

    var isAppInForeground: Boolean = true

    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(ActivityLifecycleCallback())
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when (event) {
            Lifecycle.Event.ON_START -> onAppForeground()
            Lifecycle.Event.ON_STOP -> onAppBackGround()
            else -> {}
        }
    }

    open fun onAppBackGround() {
        isAppInForeground = false
    }

    open fun onAppForeground() {
        isAppInForeground = true
    }
}
