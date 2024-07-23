package com.example.goals.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.goals.databinding.ActivityGoalsBinding
import com.example.goals.databinding.GoalCardBinding
import com.example.goals.models.Goal

class GoalCardAdapter (private val context: Context, private val itemList: MutableList<Goal>): RecyclerView.Adapter<GoalCardAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: GoalCardBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(goal: Goal) {
            binding.goalCard.setOnClickListener {
                TODO("Implement intent")
            }

            binding.goalName.setText(goal.name)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoalCardAdapter.ViewHolder {
        val binding = GoalCardBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GoalCardAdapter.ViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size
}