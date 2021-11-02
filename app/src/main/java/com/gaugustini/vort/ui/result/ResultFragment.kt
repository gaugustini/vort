package com.gaugustini.vort.ui.result

import android.os.Bundle
import android.os.SystemClock.sleep
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
import kotlinx.coroutines.Dispatchers
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
        adapter.submitList(viewModel.results)

        // TODO: Replace progress indicator with search time and if it has results.
        // Progress Indicator (3s) -> RecyclerView (3s) -> TextView
        lifecycleScope.launch(Dispatchers.Default) {
            sleep(3000)
            launch(Dispatchers.Main) {
                binding.progressIndicator.visibility = View.GONE
                binding.listResult.adapter = adapter
            }
            sleep(3000)
            launch(Dispatchers.Main) {
                binding.listResult.visibility = View.GONE
                binding.txtNoResult.visibility = View.VISIBLE
            }
        }
    }

}
