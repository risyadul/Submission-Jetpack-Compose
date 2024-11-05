package com.dicoding.submissionjetpackcompose.ui.navigation

sealed class Screen(val route: String, val title: String) {
    data object Home : Screen("home", "Home")
    data object Profile : Screen("profile", "About")
    data object Detail : Screen("detail/{id}", "Detail Character") {
        fun createRoute(id: Long) = "detail/$id"
    }

    data object Splash : Screen("splash", "")

    companion object {
        fun getAllInstances(): List<Screen> = listOf(Home, Profile, Detail, Splash)
    }
}