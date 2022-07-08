package com.gaugustini.vort.ui.setting

import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import com.gaugustini.vort.R
import com.gaugustini.vort.VortApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val theme = findPreference<ListPreference>(getString(R.string.key_theme))
        theme?.setOnPreferenceChangeListener { _, newValue ->
            VortApp.setAppTheme(newValue.toString())
            true
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menu.removeItem(R.id.settings)
    }

}
