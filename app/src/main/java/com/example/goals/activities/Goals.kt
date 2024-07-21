package com.example.goals.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.goals.databinding.ActivityGoalsBinding
import com.example.goals.fragments.AddGoalFragment
import com.example.goals.models.Goal
import com.example.goals.repositories.GoalsRepository
import com.example.goals.viewmodels.AddGoalViewModel
import java.io.IOException

class Goals : AppCompatActivity() {
    private lateinit var binding: ActivityGoalsBinding
    private lateinit var addGoalViewModel: AddGoalViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGoalsBinding.inflate(layoutInflater)

        setContentView(binding.root)

        addGoalViewModel = ViewModelProvider(this).get(AddGoalViewModel::class.java)

        binding.addGoal.setOnClickListener {
            AddGoalFragment().show(supportFragmentManager, "newGoal")
        }
        renderGoals()
    }

    private fun renderGoals () {
        val goalsRepository = GoalsRepository(this)
        goalsRepository.get()
    }
    fun onRender () {

    }
}