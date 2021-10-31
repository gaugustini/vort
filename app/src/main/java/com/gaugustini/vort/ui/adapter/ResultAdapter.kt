package com.gaugustini.vort.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gaugustini.vort.R
import com.gaugustini.vort.databinding.ListItemResultBinding
import com.gaugustini.vort.model.Result

/**
 * Adapter used in result list (RecycleView).
 */
class ResultAdapter() :
    ListAdapter<Result, ResultAdapter.ResultViewHolder>(DiffCallback<Result>()) {

    class ResultViewHolder(private val binding: ListItemResultBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Result) {
            binding.apply {
                result = item

                val inflater = LayoutInflater.from(binding.root.context)

                item.decorations.forEach {
                    val decorationText = inflater.inflate(
                        R.layout.view_item_decoration_text,
                        binding.listDecorations,
                        false
                    ) as TextView

                    val text = "${it.first} x ${it.second}"
                    decorationText.text = text

                    binding.listDecorations.addView(decorationText)
                }

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
