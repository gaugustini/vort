package com.gaugustini.vort.ui.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.gaugustini.vort.databinding.FragmentResultBinding
import com.gaugustini.vort.ui.MainViewModel
import com.gaugustini.vort.ui.adapter.ResultAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ResultFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()
    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultBinding.inflate(inflater, container, false)

        setAdapter()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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
