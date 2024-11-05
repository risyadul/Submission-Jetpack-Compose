package com.dicoding.submissionjetpackcompose.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.dicoding.submissionjetpackcompose.R
import com.dicoding.submissionjetpackcompose.ui.theme.SubmissionJetpackComposeTheme

@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    ConstraintLayout(modifier = modifier.fillMaxSize()) {
        val (imageProfile, txtFullName, txtEmail) = createRefs()
        Image(
            painter = painterResource(id = R.drawable.profile_pic),
            contentDescription = "",
            modifier = Modifier
                .size(180.dp)
                .clip(CircleShape)
                .constrainAs(imageProfile) {
                    top.linkTo(parent.top, margin = 30.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            contentScale = ContentScale.Crop
        )
        Text(
            text = "Risyadulalim",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier.constrainAs(txtFullName) {
                top.linkTo(imageProfile.bottom, margin = 32.dp)
                start.linkTo(imageProfile.start)
                end.linkTo(imageProfile.end)
            }
        )
        Text(
            text = "risanjayy@gmail.com",
            fontSize = 20.sp,
            modifier = Modifier.constrainAs(txtEmail) {
                top.linkTo(txtFullName.bottom, margin = 16.dp)
                start.linkTo(txtFullName.start)
                end.linkTo(txtFullName.end)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DetailProfilePreview() {
    SubmissionJetpackComposeTheme {
        ProfileScreen()
    }
}