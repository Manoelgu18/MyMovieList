package com.example.mymovielist

import androidx.annotation.DrawableRes
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MovieViewModel : ViewModel() {

    private val _movieListUiState = MutableStateFlow(MovieListScreenUiState())
    val movieListUiState: StateFlow<MovieListScreenUiState> = _movieListUiState.asStateFlow()

    private val _insertEditMovieScreenUiState = MutableStateFlow(InsertEditMovieScreenUiState())
    val insertEditMovieScreenUiState: StateFlow<InsertEditMovieScreenUiState> =
        _insertEditMovieScreenUiState.asStateFlow()

    private val _currentRoute = MutableStateFlow(AppScreens.MovieList.name)
    val currentRoute: StateFlow<String> = _currentRoute.asStateFlow()

    fun insertMovie(movie: Movie) {
        _movieListUiState.value = _movieListUiState.value.copy(
            movies = _movieListUiState.value.movies + movie
        )
    }

    fun onMovieCheckedChange(movie: Movie, checked: Boolean) {
        _movieListUiState.value =
            _movieListUiState.value.copy(movies = _movieListUiState.value.movies.map {
                if (it == movie) it.copy(isCompleted = checked) else it
            }.toMutableList())
    }

    fun onMovieDelete(movie: Movie) {
        _movieListUiState.value = _movieListUiState.value.copy(
            movies = _movieListUiState.value.movies.filter { it != movie }.toMutableList()
        )
    }

    fun onNameFilterChange(nameFilter: String) {
        _movieListUiState.value = _movieListUiState.value.copy(nameFilter = nameFilter)
    }
    fun updateMovie(movie: Movie) {
        _movieListUiState.value = _movieListUiState.value.copy(
            movies = _movieListUiState.value.movies.map {
                if (it == _insertEditMovieScreenUiState.value.movieToEdit) movie else it
            }
        )
    }
    fun startInsertMovie(){
        _currentRoute.value = AppScreens.InsertEditMovie.name
        _insertEditMovieScreenUiState.value = InsertEditMovieScreenUiState()
    }
    fun startEditMovie(movie: Movie){
        _currentRoute.value = AppScreens.InsertEditMovie.name
        _insertEditMovieScreenUiState.value = InsertEditMovieScreenUiState(
            name = movie.name,
            description = movie.description,
            icon = movie.icon,
            movieToEdit = movie
        )
    }
    fun onMovieNameChange(name: String){
        _insertEditMovieScreenUiState.value = insertEditMovieScreenUiState.value.copy(name = name)
    }
    fun onMovieDescriptionChange(description: String){
        _insertEditMovieScreenUiState.value = _insertEditMovieScreenUiState.value.copy(description = description)
    }
    fun onMovieCategoryIconChange(@DrawableRes icon: Int){
        _insertEditMovieScreenUiState.value = _insertEditMovieScreenUiState.value.copy(icon = icon)
    }
    fun onBackClick(){
        _currentRoute.value = AppScreens.MovieList.name
    }

    fun saveMovie() {
        val state = _insertEditMovieScreenUiState.value
        val movie = state.movieToEdit?.copy(
            name = state.name,
            description = state.description,
            icon = state.icon
        ) ?: Movie(
            name = state.name,
            description = state.description,
            icon = state.icon,
            genre = "", // se não tiver um campo de gênero, pode deixar vazio
            isCompleted = false
        )

        if (state.movieToEdit != null) {
            updateMovie(movie)
        } else {
            insertMovie(movie)
        }

        _currentRoute.value = AppScreens.MovieList.name
    }

}