package com.example.mymovielist

import androidx.annotation.DrawableRes

data class Movie(
    val name: String,
    val genre: String,
    val description: String,
    @DrawableRes val icon: Int,
    val isCompleted: Boolean = false
)
