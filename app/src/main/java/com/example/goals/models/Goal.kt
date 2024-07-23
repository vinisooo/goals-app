package com.example.goals.models

data class Goal (
    val id: Int?,
    val name: String,
    val description: String?,
    val targetAmount: Float?,
    val percentage: Int?,
    val imageUrl: String?
)