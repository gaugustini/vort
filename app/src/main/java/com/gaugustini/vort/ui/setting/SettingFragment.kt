package com.gaugustini.vort.ui.setting

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.gaugustini.vort.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
    }

}
