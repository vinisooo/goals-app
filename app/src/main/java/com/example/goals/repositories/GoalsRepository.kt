package com.example.goals.repositories

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.goals.BuildConfig
import com.example.goals.activities.Goals
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class GoalsRepository(private val context: Context) {
    private val client = OkHttpClient()
    private val baseUrl = BuildConfig.API_URL

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
                    Log.d("GoalsRepository", "Fetch goals successful")
                } else {
                    Log.e("GoalsRepository", "Failed to fetch goals: ${response.message}")
                    (context as? Goals)?.runOnUiThread {
                        Toast.makeText(context, "Failed to render goals. Try again later", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }
}
