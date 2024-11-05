package com.dicoding.submissionjetpackcompose.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dicoding.submissionjetpackcompose.data.character.Character
import com.dicoding.submissionjetpackcompose.ui.common.navigateByRoute
import com.dicoding.submissionjetpackcompose.ui.component.TopBar
import com.dicoding.submissionjetpackcompose.ui.navigation.Screen
import com.dicoding.submissionjetpackcompose.ui.screen.DetailScreen
import com.dicoding.submissionjetpackcompose.ui.screen.HomeScreen
import com.dicoding.submissionjetpackcompose.ui.screen.ProfileScreen
import com.dicoding.submissionjetpackcompose.ui.screen.SplashScreen

@Composable
fun SubmissionApp(
    modifier: Modifier = Modifier,
    navHostController: NavHostController = rememberNavController()
) {
    var query by remember {
        mutableStateOf("")
    }
    var characters by remember {
        mutableStateOf(Character.initiateDefaultCharacters())
    }
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val currentScreen = Screen.getAllInstances().find { it.route == currentRoute } ?: Screen.Home
    Scaffold(
        topBar = {
            if (currentScreen.route != Screen.Splash.route) {
                TopBar(screen = currentScreen, onBackClicked = { navHostController.navigateUp() }, gotoAboutPage =  {
                    navHostController.navigateByRoute(Screen.Profile.route)
                }, searchQuery = query) {
                    query = it
                    characters = Character.initiateDefaultCharacters().filter { item ->
                        item.name.contains(it, true)
                    }
                }
            }
        },
        modifier = modifier
    ) { pv ->
        NavHost(
            navController = navHostController,
            startDestination = Screen.Splash.route,
            modifier = Modifier.padding(pv)
        ) {
            composable(Screen.Splash.route) {
                SplashScreen(navController = navHostController)
            }
            composable(Screen.Home.route) {
                HomeScreen(characters = characters) {
                    navHostController.navigateByRoute(Screen.Detail.createRoute(it))
                }
            }
            composable(
                Screen.Detail.route,
                arguments = listOf(navArgument("id") { type = NavType.LongType })
            ) { backStackEntry ->
                val id = backStackEntry.arguments?.getLong("id") ?: -1L
                val item = Character.initiateDefaultCharacters().find { it.id == id }
                DetailScreen(item = item)
            }
            composable(Screen.Profile.route) {
                ProfileScreen()
            }
        }
    }
}