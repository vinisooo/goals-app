package com.example.goals.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.goals.databinding.CheckChipBinding
import com.example.goals.models.Check
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class CheckChipAdapter (private val context: Context, private val itemList: List<Check>): BaseAdapter() {
    override fun getCount(): Int = itemList.size

    override fun getItem(position: Int): Check = itemList[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val binding: CheckChipBinding
        val view: View

        if (convertView == null) {
            binding = CheckChipBinding.inflate(LayoutInflater.from(context), parent, false)
            view = binding.root
            view.tag = binding
        } else {
            binding = convertView.tag as CheckChipBinding
            view = convertView
        }

        binding.checkChip.text = itemList[position].name
        binding.checkChip.isChecked = itemList[position].checked

        binding.checkChip.isCloseIconVisible =  true

        return view
    }

    fun addToCheckChips (chipGroup: ChipGroup) {
        for (i in 0 until count) {
            val chip = getView(i, null, chipGroup) as Chip
            chipGroup.addView(chip)
        }
    }
}