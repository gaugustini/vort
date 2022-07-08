package com.gaugustini.vort

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceManager
import com.google.android.material.color.DynamicColors
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class VortApp : Application() {

    override fun onCreate() {
        theme()
        DynamicColors.applyToActivitiesIfAvailable(this)
    }

    private fun theme() {
        val preferences = PreferenceManager.getDefaultSharedPreferences(this)
        val value = preferences.getString(getString(R.string.key_theme), "system")
        setAppTheme(value)
    }

    companion object {

        fun setAppTheme(value: String?) {
            when (value) {
                "light" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                "dark" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                else -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            }
        }

    }

}
