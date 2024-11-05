package com.dicoding.submissionjetpackcompose.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dicoding.submissionjetpackcompose.ui.navigation.Screen
import com.dicoding.submissionjetpackcompose.ui.theme.SubmissionJetpackComposeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    screen: Screen,
    onBackClicked: () -> Unit,
    gotoAboutPage: () -> Unit,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit
) {
    TopAppBar(
        title = {
            if (Screen.Home.route == screen.route) {
                SearchBarHeroes(query = searchQuery, onQueryChange = onSearchQueryChange)
            } else {
                Text(text = screen.title)
            }
        },
        navigationIcon = {
            if (screen.route != Screen.Home.route) {
                IconButton(onClick = { onBackClicked.invoke() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        },
        actions = {
            if (screen.route == Screen.Home.route) {
                IconButton(onClick = { gotoAboutPage() }) {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "About Icon",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun TopBarPreview() {
    SubmissionJetpackComposeTheme {
        TopBar(
            screen = Screen.Home,
            onBackClicked = { /*TODO*/ },
            gotoAboutPage = { /*TODO*/ },
            searchQuery = ""
        ) {

        }
    }
}