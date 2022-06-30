package com.gaugustini.vort

import android.app.Application
import com.google.android.material.color.DynamicColors
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class VortApp : Application() {

    override fun onCreate() {
        DynamicColors.applyToActivitiesIfAvailable(this)
    }

}
