package com.example.goals.repositories

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.goals.BuildConfig
import com.example.goals.activities.Goals
import com.example.goals.activities.GoalsCallback
import com.example.goals.models.Goal
import com.google.gson.reflect.TypeToken
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONArray
import org.json.JSONStringer
import java.io.IOException

class GoalsRepository(private val context: Context) {
    private val client = OkHttpClient()
    private val baseUrl = "http://192.168.100.4:8080/api"

    fun get() {
        val request = Request.Builder().url("$baseUrl/goals").build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("GoalsRepository", "Failed to fetch goals", e)
                (context as? Goals)?.runOnUiThread {
                    Toast.makeText(context, "Failed to fetch goals", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    response.body?.let { responseBody ->
                        val goalsResponse = parseJSONGoals(responseBody.string())

                        (context as? Goals)?.runOnUiThread {
                            context.onRenderGoals(goalsResponse)
                        }
                    }
                } else {
                    Log.e("GoalsRepository", "Failed to fetch goals: ${response.message}")
                    (context as? Goals)?.runOnUiThread {
                        Toast.makeText(context, "Failed to render goals. Try again later", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    fun parseJSONGoals (goalsJSON: String): MutableList<Goal> {
        val goals = mutableListOf<Goal>()
        val jsonArray = JSONArray(goalsJSON)

        for (i in 0 until jsonArray.length()) {
            val currentGoal = jsonArray.getJSONObject(i)

            val goal = Goal(
                id = currentGoal.getInt("id"),
                name = currentGoal.getString("name"),
                description = currentGoal.getString("description"),
                imageUrl = currentGoal.optString("image_url", ""),
                percentage = currentGoal.getInt("percentage"),
                targetAmount = currentGoal.getDouble("target_amount").toFloat()
            )

            goals.add(goal)
        }

        return goals
    }
}
