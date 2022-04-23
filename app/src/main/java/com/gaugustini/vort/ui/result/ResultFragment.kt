package com.gaugustini.vort.ui.result

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.gaugustini.vort.R
import com.gaugustini.vort.databinding.FragmentResultBinding
import com.gaugustini.vort.ui.MainViewModel
import com.gaugustini.vort.ui.adapter.ResultAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ResultFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentResultBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentResultBinding.inflate(inflater, container, false)

        setAdapter()

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_result, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.setting -> {
                NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
            }
        }
        return true
    }

    private fun setAdapter() {
        val adapter = ResultAdapter()
        binding.listResult.adapter = adapter

        // TODO: Show text "No Results found" when the search has no results.
        lifecycleScope.launch {
            viewModel.getResults().collect {
                binding.progressIndicator.visibility = if (it.isEmpty()) View.VISIBLE else View.GONE
                adapter.submitList(it)
            }
        }

    }

}
