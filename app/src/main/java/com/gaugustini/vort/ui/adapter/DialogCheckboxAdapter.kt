package com.gaugustini.vort.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckBox

/**
 * Adapter used in dialog with checkboxes.
 */
class DialogCheckboxAdapter(
    context: Context,
    layout: Int,
    items: List<String>,
    checkedItems: BooleanArray,
) : ArrayAdapter<String>(context, layout, items) {

    private val mItems = items
    private val mCheckedItems = checkedItems
    private val mLayout = layout

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = LayoutInflater.from(context)
        val view = convertView ?: inflater.inflate(mLayout, parent, false)

        val checkBox = view as CheckBox
        checkBox.text = getItem(position).toString()

        val index = mItems.indexOf(checkBox.text.toString())

        checkBox.isChecked = mCheckedItems[index]
        checkBox.setOnClickListener {
            mCheckedItems[index] = checkBox.isChecked
        }

        return view
    }

    fun getCheckedItems(): List<String> {
        val list = mutableListOf<String>()
        mCheckedItems.forEachIndexed { index, value ->
            if (value) {
                list.add(mItems[index])
            }
        }

        return list
    }

}
