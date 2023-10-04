package com.diego.marvel.ui.loading

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Loading() {
    var circleScale by remember {
        mutableStateOf(0f)
    }
    val circleScaleAnimation = animateFloatAsState(
        targetValue = circleScale, animationSpec = infiniteRepeatable(
            animation = tween(1000)
        )
    )
    LaunchedEffect(Unit) {
        circleScale = 1f
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(80.dp)
                .scale(circleScaleAnimation.value)
                .border(
                    width = 4.dp,
                    color = Color.Red,
                    shape = CircleShape
                )
        )
    }
}

@Preview
@Composable
fun LoadingPreview() {
    Loading()
}