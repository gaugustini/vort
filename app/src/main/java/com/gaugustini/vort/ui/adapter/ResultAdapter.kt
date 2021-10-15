package com.gaugustini.vort.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gaugustini.vort.databinding.ListItemResultBinding
import com.gaugustini.vort.model.Result

class ResultAdapter() :
    ListAdapter<Result, ResultAdapter.ResultViewHolder>(DiffCallback<Result>()) {

    class ResultViewHolder(private val binding: ListItemResultBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Result) {
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
