package com.example.goals

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import com.example.goals.activities.Goals
import com.example.goals.databinding.ActivityMainBinding

class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        loadGoals()
    }

    private fun loadGoals () {
        Handler(Looper.getMainLooper()).postDelayed({
            val goalsIntent = Intent(this, Goals::class.java)
            startActivity(goalsIntent)
            finish()
        }, 3000)
    }
}
