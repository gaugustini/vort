package com.gaugustini.vort.ui.search

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.gaugustini.vort.R
import com.gaugustini.vort.databinding.DialogSearchBinding
import com.gaugustini.vort.ui.MainViewModel
import com.gaugustini.vort.ui.adapter.DialogCheckboxAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchDialog : DialogFragment() {

    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var binding: DialogSearchBinding
    private lateinit var adapter: DialogCheckboxAdapter

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogSearchBinding.inflate(layoutInflater)

        setAdapter()
        setSearchView()

        return MaterialAlertDialogBuilder(requireContext())
            .setView(binding.root)
            .setPositiveButton(getString(R.string.ok)) { _, _ ->
                if (viewModel.type == 1) {
                    viewModel.mySkillsBlade = adapter.getCheckedItems()
                } else {
                    viewModel.mySkillsGunner = adapter.getCheckedItems()
                }
                viewModel.done.value = true
            }
            .setNegativeButton(getString(R.string.cancel)) { _, _ -> }
            .create()
    }

    private fun setAdapter() {
        adapter = DialogCheckboxAdapter(
            context = requireContext(),
            layout = R.layout.view_item_simple_checkbox,
            items = if (viewModel.type == 1) {
                viewModel.skillsBlade.keys.toList().sorted()
            } else {
                viewModel.skillsGunner.keys.toList().sorted()
            },
            checkedItems = if (viewModel.type == 1) {
                mySkillsBoolean(
                    viewModel.skillsBlade.keys.toList().sorted(), viewModel.mySkillsBlade
                )
            } else {
                mySkillsBoolean(
                    viewModel.skillsGunner.keys.toList().sorted(), viewModel.mySkillsGunner
                )
            }
        )

        binding.listAllSkills.adapter = adapter
    }

    private fun setSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return onQueryTextChange(query)
            }

            override fun onQueryTextChange(newtext: String): Boolean {
                adapter.filter.filter(newtext)
                return true
            }
        })
    }

    private fun mySkillsBoolean(skills: List<String>, mySkills: List<String>): BooleanArray {
        return buildList {
            skills.forEach {
                if (mySkills.contains(it)) this.add(true) else this.add(false)
            }
        }.toBooleanArray()
    }

}
