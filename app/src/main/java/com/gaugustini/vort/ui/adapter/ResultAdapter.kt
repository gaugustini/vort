package com.gaugustini.vort.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gaugustini.vort.databinding.ListItemResultBinding
import com.gaugustini.vort.model.ResultWithNames

/**
 * Adapter used in result list (RecyclerView).
 */
class ResultAdapter :
    ListAdapter<ResultWithNames, ResultAdapter.ResultViewHolder>(DiffCallback<ResultWithNames>()) {

    class ResultViewHolder(private val binding: ListItemResultBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ResultWithNames) {
            binding.apply {
                result = item
                executePendingBindings()
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
        return ResultViewHolder(
            ListItemResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        val result = getItem(position)
        holder.bind(result)
    }

}
