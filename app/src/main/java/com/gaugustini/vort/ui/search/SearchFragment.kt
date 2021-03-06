package com.gaugustini.vort.ui.search

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.gaugustini.vort.R
import com.gaugustini.vort.databinding.FragmentSearchBinding
import com.gaugustini.vort.ui.MainViewModel
import com.gaugustini.vort.ui.adapter.SpinnerAdapter
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint

// TODO: Add default values on Village/Hunter Ranks and Weapon Slots;
//  Prevent crash when empty texts.
@AndroidEntryPoint
class SearchFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)

        setAdapters()
        setOnClickListeners()
        setObserves()
        showMySkills(viewModel.mySkillsBlade)

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_search, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.setting -> {
                NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
            }
        }
        return true
    }

    private fun setAdapters() {
        binding.actxtHunterRank.setAdapter(
            SpinnerAdapter(
                requireContext(), R.layout.view_item_exposed_dropdown_menu, viewModel.ranks
            )
        )

        binding.actxtVillageRank.setAdapter(
            SpinnerAdapter(
                requireContext(), R.layout.view_item_exposed_dropdown_menu, viewModel.ranks
            )
        )

        binding.actxtWeaponSlots.setAdapter(
            SpinnerAdapter(
                requireContext(), R.layout.view_item_exposed_dropdown_menu, viewModel.slots
            )
        )

    }

    private fun setOnClickListeners() {
        binding.btnSkills.setOnClickListener {
            this.findNavController().navigate(SearchFragmentDirections.actionSearchToDialog())
        }

        binding.btnSearch.setOnClickListener {
            viewModel.search(
                hunterRank = binding.actxtHunterRank.text.toString().toInt(),
                villageRank = binding.actxtVillageRank.text.toString().toInt(),
                weaponSlot = binding.actxtWeaponSlots.text.toString().toInt(),
                gender = binding.radioGpGender.checkedRadioButtonId,
            )
            this.findNavController().navigate(SearchFragmentDirections.actionSearchToResult())
        }

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> { // Blademaster
                        viewModel.type = 1
                        showMySkills(viewModel.mySkillsBlade)
                        binding.btnSearch.isEnabled = viewModel.mySkillsBlade.isNotEmpty()
                    }
                    1 -> { // Gunner
                        viewModel.type = 2
                        showMySkills(viewModel.mySkillsGunner)
                        binding.btnSearch.isEnabled = viewModel.mySkillsGunner.isNotEmpty()
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {
                onTabSelected(tab)
            }
        })

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
                R.layout.view_item_skill_text,
                binding.listSkill,
                false
            ) as TextView

            skillText.text = it

            binding.listSkill.addView(skillText)
        }

    }

}
