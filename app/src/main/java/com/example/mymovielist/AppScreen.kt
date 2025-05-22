package com.example.mymovielist

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mymovielist.ui.theme.MyMovieListTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieListApp(
    appViewModel: MovieViewModel = viewModel()
) {
    val uiState = appViewModel.movieListUiState.collectAsState()
    val currentRoute = appViewModel.currentRoute.collectAsState()
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("MyMovieList") },
                actions = {
                    if (currentRoute.value == AppScreens.MovieList.name) {
                        TextField(
                            value = uiState.value.nameFilter,
                            onValueChange = appViewModel::onNameFilterChange,
                            label = { Text("Filtrar Filmes") }
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    if (currentRoute.value == AppScreens.MovieList.name) {
                        appViewModel.startInsertMovie()
                        navController.navigate(AppScreens.InsertEditMovie.name)
                    } else if (currentRoute.value == AppScreens.InsertEditMovie.name) {
                        appViewModel.saveMovie()
                        appViewModel.onBackClick()
                        navController.popBackStack()
                    }
                }
            ) {
                Text(
                    if (currentRoute.value == AppScreens.MovieList.name) "+" else "Salvar"
                )
            }
        }
    ) { innerPadding ->
        MovieListNavHost(
            modifier = Modifier.padding(innerPadding),
            navController = navController,
            viewModel = appViewModel
        )
    }
}

@Composable
fun MovieListNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: MovieViewModel,
) {
    NavHost(
        navController = navController,
        startDestination = AppScreens.MovieList.name
    ) {
        composable(AppScreens.MovieList.name) {
            val uiState = viewModel.movieListUiState.collectAsState()
            MovieListScreen(
                modifier = modifier,
                movies = uiState.value.filteredMovies,
                onMovieCheckedChange = viewModel::onMovieCheckedChange,
                onMovieDeleted = viewModel::onMovieDelete,
                onMovieEdit = { movie: Movie ->
                    viewModel.startEditMovie(movie)
                    navController.navigate(AppScreens.InsertEditMovie.name)
                }
            )
        }

        composable(AppScreens.InsertEditMovie.name) {
            val uiState = viewModel.insertEditMovieScreenUiState.collectAsState()
            InsertEditMovieScreen(
                modifier = modifier,
                uiState = uiState.value,
                onNameChange = viewModel::onMovieNameChange,
                onDescriptionChange = viewModel::onMovieDescriptionChange,
                onCategoryIconChange = viewModel::onMovieCategoryIconChange,
                onCancel = {
                    viewModel.onBackClick()
                    navController.popBackStack()
                }
            )
        }
    }
}

enum class AppScreens {
    MovieList,
    InsertEditMovie
}

@Preview(showBackground = true)
@Composable
fun MovieListAppPreview() {
    MyMovieListTheme {
        MovieListApp()
    }
}