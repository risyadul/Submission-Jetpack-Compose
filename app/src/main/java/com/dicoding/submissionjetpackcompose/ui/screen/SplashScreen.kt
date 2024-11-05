package com.dicoding.submissionjetpackcompose.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dicoding.submissionjetpackcompose.R
import com.dicoding.submissionjetpackcompose.ui.navigation.Screen
import com.dicoding.submissionjetpackcompose.ui.theme.SubmissionJetpackComposeTheme
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(modifier: Modifier = Modifier, navController: NavController) {
    Box(modifier = modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.img_splash),
            contentDescription = "Splash Screen",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }

    LaunchedEffect(key1 = true) {
        delay(2000)
        navController.navigate(Screen.Home.route) {
            popUpTo("splash") { inclusive = true }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    SubmissionJetpackComposeTheme {
        SplashScreen(navController = rememberNavController())
    }
}