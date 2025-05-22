package com.example.mymovielist

import androidx.annotation.DrawableRes
import com.example.mymovielist.R

data class InsertEditMovieScreenUiState(
    val name: String = "",
    val description: String = "",
    @DrawableRes val icon: Int = R.drawable.acao,
    val movieToEdit: Movie? = null
)
