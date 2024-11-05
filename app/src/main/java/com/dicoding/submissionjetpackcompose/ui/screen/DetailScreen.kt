package com.dicoding.submissionjetpackcompose.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil3.compose.AsyncImage
import com.dicoding.submissionjetpackcompose.data.character.Character
import com.dicoding.submissionjetpackcompose.ui.theme.SubmissionJetpackComposeTheme

@Composable
fun DetailScreen(modifier: Modifier = Modifier, item: Character?) {
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(bottom = 24.dp)
        ) {
            val (
                imageBackground, imageProfile, characterName, trueNameLabel, trueName,
                akaLabel, aka, speciesLabel, species, alignmentLabel, alignment,
                japaneseVaLabel, japaneseVa, profileLabel, characterProfile
            ) = createRefs()

            AsyncImage(
                model = item?.bannerUrl,
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(309.dp)
                    .constrainAs(imageBackground) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                contentScale = ContentScale.Crop
            )

            AsyncImage(
                model = item?.thumbnailUrl,
                contentDescription = "",
                modifier = Modifier
                    .size(106.dp)
                    .clip(CircleShape)
                    .constrainAs(imageProfile) {
                        start.linkTo(imageBackground.start, margin = 16.dp)
                        bottom.linkTo(characterName.bottom)
                    },
                contentScale = ContentScale.Crop
            )

            Text(
                text = item?.name.orEmpty(),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.constrainAs(characterName) {
                    top.linkTo(imageBackground.bottom, margin = 16.dp)
                    start.linkTo(imageProfile.end, margin = 16.dp)
                }
            )

            Text(
                text = "True Name :",
                fontSize = 16.sp,
                modifier = Modifier.constrainAs(trueNameLabel) {
                    top.linkTo(characterName.bottom, margin = 16.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                }
            )

            Text(
                text = item?.trueName.orEmpty(),
                fontSize = 16.sp,
                modifier = Modifier.constrainAs(trueName) {
                    top.linkTo(characterName.bottom, margin = 16.dp)
                    start.linkTo(trueNameLabel.end, margin = 8.dp)
                    end.linkTo(parent.end, margin = 16.dp)
                    width = Dimension.fillToConstraints
                }
            )

            Text(
                text = "A.K.A :",
                fontSize = 16.sp,
                modifier = Modifier.constrainAs(akaLabel) {
                    top.linkTo(trueName.bottom, margin = 16.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                }
            )

            Text(
                text = item?.characterAlias.orEmpty(),
                fontSize = 16.sp,
                modifier = Modifier.constrainAs(aka) {
                    top.linkTo(trueName.bottom, margin = 16.dp)
                    start.linkTo(akaLabel.end, margin = 8.dp)
                    end.linkTo(parent.end, margin = 16.dp)
                    width = Dimension.fillToConstraints
                }
            )

            Text(
                text = "Species :",
                fontSize = 16.sp,
                modifier = Modifier.constrainAs(speciesLabel) {
                    top.linkTo(aka.bottom, margin = 8.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                }
            )

            Text(
                text = item?.species.orEmpty(),
                fontSize = 16.sp,
                modifier = Modifier.constrainAs(species) {
                    top.linkTo(speciesLabel.top)
                    start.linkTo(speciesLabel.end, margin = 8.dp)
                    end.linkTo(parent.end, margin = 16.dp)
                    width = Dimension.fillToConstraints
                }
            )

            Text(
                text = "Alignment :",
                fontSize = 16.sp,
                modifier = Modifier.constrainAs(alignmentLabel) {
                    top.linkTo(species.bottom, margin = 8.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                }
            )

            Text(
                text = item?.alignment.orEmpty(),
                fontSize = 16.sp,
                modifier = Modifier.constrainAs(alignment) {
                    top.linkTo(alignmentLabel.top)
                    start.linkTo(alignmentLabel.end, margin = 8.dp)
                    end.linkTo(parent.end, margin = 16.dp)
                    width = Dimension.fillToConstraints
                }
            )

            Text(
                text = "Japanese VA :",
                fontSize = 16.sp,
                modifier = Modifier.constrainAs(japaneseVaLabel) {
                    top.linkTo(alignment.bottom, margin = 8.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                }
            )

            Text(
                text = item?.japaneseVA.orEmpty(),
                fontSize = 16.sp,
                modifier = Modifier.constrainAs(japaneseVa) {
                    top.linkTo(japaneseVaLabel.top)
                    start.linkTo(japaneseVaLabel.end, margin = 8.dp)
                    end.linkTo(parent.end, margin = 16.dp)
                    width = Dimension.fillToConstraints
                }
            )

            Text(
                text = "Profile :",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.constrainAs(profileLabel) {
                    top.linkTo(japaneseVa.bottom, margin = 16.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                }
            )

            Text(
                text = item?.description.orEmpty(),
                fontSize = 16.sp,
                modifier = Modifier.constrainAs(characterProfile) {
                    top.linkTo(profileLabel.bottom, margin = 4.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                    end.linkTo(parent.end, margin = 16.dp)
                    width = Dimension.fillToConstraints
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    SubmissionJetpackComposeTheme {
        DetailScreen(item = Character.initiateDefaultCharacters()[0])
    }
}