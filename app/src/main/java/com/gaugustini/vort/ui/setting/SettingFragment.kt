package com.gaugustini.vort.ui.setting

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.view.MenuProvider
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import com.gaugustini.vort.R
import com.gaugustini.vort.VortApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingFragment : PreferenceFragmentCompat() {

    private val menu = object : MenuProvider {

        override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        }

        override fun onMenuItemSelected(item: MenuItem): Boolean {
            return true
        }

        override fun onPrepareMenu(menu: Menu) {
            menu.removeItem(R.id.settings)
        }

    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().addMenuProvider(menu, viewLifecycleOwner)

        val theme = findPreference<ListPreference>(getString(R.string.key_theme))
        theme?.setOnPreferenceChangeListener { _, newValue ->
            VortApp.setAppTheme(newValue.toString())
            true
        }
    }

}
