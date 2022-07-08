package com.gaugustini.vort.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.widget.doOnTextChanged
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.gaugustini.vort.R
import com.gaugustini.vort.databinding.FragmentSearchBinding
import com.gaugustini.vort.ui.MainViewModel
import com.gaugustini.vort.ui.adapter.SpinnerAdapter
import com.gaugustini.vort.utils.dataStore
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)

        setAdapters()
        setObserves()
        restoreData()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.actxtHunterRank.doOnTextChanged { text, _, _, _ ->
            saveData(getString(R.string.key_hunter_rank), text.toString().toInt())
        }
        binding.actxtVillageRank.doOnTextChanged { text, _, _, _ ->
            saveData(getString(R.string.key_village_rank), text.toString().toInt())
        }
        binding.actxtWeaponSlots.doOnTextChanged { text, _, _, _ ->
            saveData(getString(R.string.key_weapon_slots), text.toString().toInt())
        }
        binding.radioGpGender.setOnCheckedChangeListener { _, _ ->
            if (binding.radioBtnMale.isChecked) {
                saveData(getString(R.string.key_gender), 1)
            } else {
                saveData(getString(R.string.key_gender), 2)
            }
        }
        binding.btnSkills.setOnClickListener {
            findNavController().navigate(SearchFragmentDirections.actionSearchToDialog())
        }
        binding.btnSearch.setOnClickListener {
            viewModel.search(
                hunterRank = binding.actxtHunterRank.text.toString().toInt(),
                villageRank = binding.actxtVillageRank.text.toString().toInt(),
                weaponSlot = binding.actxtWeaponSlots.text.toString().toInt(),
                gender = if (binding.radioBtnMale.isChecked) 1 else 2,
            )
            findNavController().navigate(SearchFragmentDirections.actionSearchToResult())
        }
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> { // Blademaster
                        saveData(getString(R.string.key_type), 1)
                        viewModel.type = 1
                        showMySkills(viewModel.mySkillsBlade)
                        binding.btnSearch.isEnabled = viewModel.mySkillsBlade.isNotEmpty()
                    }
                    1 -> { // Gunner
                        saveData(getString(R.string.key_type), 2)
                        viewModel.type = 2
                        showMySkills(viewModel.mySkillsGunner)
                        binding.btnSearch.isEnabled = viewModel.mySkillsGunner.isNotEmpty()
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setAdapters() {
        binding.actxtHunterRank.setAdapter(
            SpinnerAdapter(
                requireContext(), R.layout.view_item_simple_text, viewModel.ranks
            )
        )
        binding.actxtVillageRank.setAdapter(
            SpinnerAdapter(
                requireContext(), R.layout.view_item_simple_text, viewModel.ranks
            )
        )
        binding.actxtWeaponSlots.setAdapter(
            SpinnerAdapter(
                requireContext(), R.layout.view_item_simple_text, viewModel.slots
            )
        )
    }

    private fun setObserves() {
        viewModel.done.observe(viewLifecycleOwner) {
            if (viewModel.type == 1) {
                showMySkills(viewModel.mySkillsBlade)
                binding.btnSearch.isEnabled = viewModel.mySkillsBlade.isNotEmpty()
            } else {
                showMySkills(viewModel.mySkillsGunner)
                binding.btnSearch.isEnabled = viewModel.mySkillsGunner.isNotEmpty()
            }
        }
    }

    private fun showMySkills(mySkills: List<String>) {
        binding.listSkill.removeAllViews()

        mySkills.forEach {
            val skillText = layoutInflater.inflate(
                R.layout.view_item_simple_text,
                binding.listSkill,
                false
            ) as TextView

            skillText.text = it

            binding.listSkill.addView(skillText)
        }
    }

    private fun saveData(key: String, value: Int) {
        lifecycleScope.launch(Dispatchers.IO) {
            val dataStoreKey = intPreferencesKey(key)
            requireContext().dataStore.edit { dataStore ->
                dataStore[dataStoreKey] = value
            }
        }
    }

    private fun restoreData() {
        lifecycleScope.launch(Dispatchers.Main) {
            val keyHunterRank = intPreferencesKey(getString(R.string.key_hunter_rank))
            val keyVillageRank = intPreferencesKey(getString(R.string.key_village_rank))
            val keyWeaponSlots = intPreferencesKey(getString(R.string.key_weapon_slots))
            val keyGender = intPreferencesKey(getString(R.string.key_gender))
            val keyType = intPreferencesKey(getString(R.string.key_type))
            val preferences = requireContext().dataStore.data.first()

            binding.actxtHunterRank.setText((preferences[keyHunterRank] ?: 9).toString())
            binding.actxtVillageRank.setText((preferences[keyVillageRank] ?: 9).toString())
            binding.actxtWeaponSlots.setText((preferences[keyWeaponSlots] ?: 0).toString())
            when (preferences[keyGender]) {
                2 -> binding.radioBtnFemale.isChecked = true
                else -> binding.radioBtnMale.isChecked = true
            }
            val tab = binding.tabLayout.getTabAt((preferences[keyType]?.minus(1)) ?: 0)
            binding.tabLayout.selectTab(tab)
        }
    }

}
