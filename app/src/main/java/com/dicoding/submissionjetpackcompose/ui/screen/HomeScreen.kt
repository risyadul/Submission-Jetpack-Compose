package com.dicoding.submissionjetpackcompose.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.dicoding.submissionjetpackcompose.R
import com.dicoding.submissionjetpackcompose.data.character.Character
import com.dicoding.submissionjetpackcompose.ui.theme.SubmissionJetpackComposeTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    characters: List<Character>,
    gotoDetail: (Long) -> Unit,
) {
    val lazyState = rememberLazyListState()
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        CharacterList(lazyState = lazyState, characters = characters) {
            gotoDetail(it)
        }
    }
}

@Composable
fun CharacterList(
    modifier: Modifier = Modifier,
    lazyState: LazyListState,
    characters: List<Character>,
    gotoDetail: (Long) -> Unit
) {
    LazyColumn(
        state = lazyState,
        modifier = modifier,
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(characters, key = { it.trueName }) {
            CharacterItem(item = it, modifier = Modifier.clickable { gotoDetail(it.id) })
        }
    }
}

@Composable
fun CharacterItem(item: Character, modifier: Modifier = Modifier) {
    Row(modifier = modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(item.thumbnailUrl)
                .crossfade(true)
                .build(),
            contentDescription = "",
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop,
        )
        Column(modifier = Modifier.padding(horizontal = 8.dp)) {
            Text(text = item.name, fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Text(
                text = stringResource(id = R.string.true_name, item.trueName),
                modifier.padding(top = 8.dp)
            )
            Text(
                text = stringResource(id = R.string.species, item.species),
                modifier.padding(top = 8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CharacterListPreview() {
    SubmissionJetpackComposeTheme {
        CharacterItem(item = Character.initiateDefaultCharacters()[0])
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    SubmissionJetpackComposeTheme {
        HomeScreen(characters = listOf()) {

        }
    }
}