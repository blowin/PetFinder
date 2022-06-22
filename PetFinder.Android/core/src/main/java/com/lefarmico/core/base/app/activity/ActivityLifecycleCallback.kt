package com.lefarmico.core.base.app.activity

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.lefarmico.core.extension.classTag
import timber.log.Timber

class ActivityLifecycleCallback : Application.ActivityLifecycleCallbacks {
    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        Timber.tag(activity.classTag).d("onCreate()")
        activity.registerFragmentLifecycleCallbacks()
    }

    override fun onActivityStarted(activity: Activity) {
        Timber.tag(activity.classTag).d("onStart()")
    }

    override fun onActivityResumed(activity: Activity) {
        Timber.tag(activity.classTag).d("onResume()")
    }

    override fun onActivityPaused(activity: Activity) {
        Timber.tag(activity.classTag).d("onPause()")
    }

    override fun onActivityStopped(activity: Activity) {
        Timber.tag(activity.classTag).d("onStop()")
    }

    override fun onActivitySaveInstanceState(activity: Activity, savedInstanceState: Bundle) {
        Timber.tag(activity.classTag).d("onSaveInstanceState()")
    }

    override fun onActivityDestroyed(activity: Activity) {
        Timber.tag(activity.classTag).d("onDestroy()")
    }
}

fun Activity.registerFragmentLifecycleCallbacks() {
    if (this is FragmentActivity) {
        supportFragmentManager
            .registerFragmentLifecycleCallbacks(
                object : FragmentManager.FragmentLifecycleCallbacks() {
                    override fun onFragmentViewCreated(
                        fm: FragmentManager,
                        f: Fragment,
                        v: View,
                        savedInstanceState: Bundle?
                    ) {
                        super.onFragmentViewCreated(fm, f, v, savedInstanceState)
                        Timber.tag(f.classTag).d("onCreateView()")
                    }

                    override fun onFragmentResumed(fm: FragmentManager, f: Fragment) {
                        super.onFragmentResumed(fm, f)
                        Timber.tag(f.classTag).d("onResume()")
                    }

                    override fun onFragmentPaused(fm: FragmentManager, f: Fragment) {
                        super.onFragmentPaused(fm, f)
                        Timber.tag(f.classTag).d("onPause()")
                    }

                    override fun onFragmentViewDestroyed(fm: FragmentManager, f: Fragment) {
                        super.onFragmentViewDestroyed(fm, f)
                        Timber.tag(f.classTag).d("onDestroyView()")
                    }
                },
                true
            )
    }
}
