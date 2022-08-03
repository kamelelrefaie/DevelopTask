package com.example.developnetworktask.presentation.login.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun GradientButton(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color,
    gradient: Brush,
    onClick: () -> Unit
) {
    Button(
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        contentPadding = PaddingValues(),
        onClick = { onClick() },
        modifier = modifier.height(48.dp),
        shape = RoundedCornerShape(40.dp),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 10.dp,
            pressedElevation = 5.dp
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .background(gradient)
                .padding(horizontal = 32.dp, vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                color = textColor
            )
        }
    }
}

@Composable
fun PreviewGradientButton() {
    GradientButton(
        text = "Log in",
        textColor = Color.White,
        gradient = Brush.horizontalGradient(
            listOf(
                Color(0xFFC4A0F4),
                Color(0xFFCF4CB9),
                Color(0xFFE60B41)
            )
        )
    ) {

    }
}