package com.opn3r.android.enimationstudy.feature.main

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@Composable
fun MainScreen(
    modifier: Modifier = Modifier
) {
    var offsetx by remember { mutableFloatStateOf(0f) }
    var offsety by remember { mutableFloatStateOf(0f) }
    var visible by remember { mutableStateOf(true) }
    var transparent by remember { mutableStateOf(true) }
    val show by animateFloatAsState(
        targetValue = if(transparent) 1.0F else 0F, label = ""
    )
    var backgroundColor by remember { mutableStateOf(false) }
    val animatedColor by animateColorAsState(
        targetValue = if (backgroundColor) Color.Black else Color.White
    )
    var expanded by remember { mutableStateOf(true) }
    var offsetX by remember { mutableStateOf(0.dp) }
    var offsetY by remember { mutableStateOf(0.dp) }
    var isMoved by remember { mutableStateOf(false) }



    Box(
        modifier = modifier
            .fillMaxSize()
            .background(animatedColor)
    ) {
        Box(
            modifier = Modifier
                .offset { IntOffset(offsetx.roundToInt(), offsety.roundToInt()) }
                .size(100.dp)
                .background(color = Color.Yellow)
                .pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->
                        change.consume()
                        offsetx += dragAmount.x
                        offsety += dragAmount.y
                        Log.d("크기", "MainScreen: $offsetx ")

                    }
                }
        )
        AnimatedVisibility(
            visible,
            modifier = modifier
                .align(Alignment.Center),
            enter = slideInVertically(
                initialOffsetY = { fullHeight -> -fullHeight },  // 오른쪽에서 등장
                animationSpec = tween(
                    durationMillis = 150,  // 애니메이션 시간
                    easing = FastOutSlowInEasing  // 가속도 곡선
                )
            ),
            exit = slideOutHorizontally(
                targetOffsetX = { fullWidth -> -fullWidth },  // 왼쪽으로 사라짐
                animationSpec = tween(
                    durationMillis = 150,
                    delayMillis = 50  // 시작 전 대기시간
                )
            )
        ) {
            Box(
                modifier = modifier
                    .size(100.dp)
                    .background(color = Color.Red)
                    .clickable(
                        onClick = {
                            visible = !visible
                        }
                    )
            )
        }
        Box(
            modifier = modifier
                .size(100.dp)
                .alpha(show)
                .background(color = Color.Blue)
                .clickable(
                    onClick = {
                        transparent = !transparent
                    }
                )
                .align(alignment = Alignment.TopEnd)
        )

        Box(
            modifier = modifier
                .animateContentSize()
                .size(if (expanded) 100.dp else 50.dp)
                .background(color = Color.Green)
                .clickable(
                    onClick = {
                        expanded = !expanded
                    }
                )
                .align(alignment = Alignment.CenterEnd)
        )
        Box(
            modifier = modifier
                .offset(
                    x = animateDpAsState(targetValue = offsetX).value,
                    y = animateDpAsState(targetValue = offsetY).value
                )
                .size(100.dp)
                .background(color = Color.Gray)
                .align(alignment = Alignment.CenterStart)
        )

        Column(
            modifier = modifier
                .fillMaxWidth()
                .align(alignment = Alignment.BottomCenter)
        ) {

            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
                    .padding(bottom = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        visible = !visible
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Blue,
                        contentColor = Color.White,
                        disabledContainerColor = Color.Gray,
                        disabledContentColor = Color.DarkGray
                    )
                ) {
                    Text(
                        text = "visible"
                    )
                }

                Button(
                    onClick = {
                        transparent = !transparent
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Blue,
                        contentColor = Color.White,
                        disabledContainerColor = Color.Gray,
                        disabledContentColor = Color.DarkGray
                    ),
                ) {
                    Text(
                        text = "transparent"
                    )
                }

                Button(
                    onClick = {
                        backgroundColor = !backgroundColor
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Blue,
                        contentColor = Color.White,
                        disabledContainerColor = Color.Gray,
                        disabledContentColor = Color.DarkGray
                    ),
                ) {
                    Text(
                        text = "background"
                    )
                }
            }
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
                    .padding(bottom = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        expanded = !expanded
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Blue,
                        contentColor = Color.White,
                        disabledContainerColor = Color.Gray,
                        disabledContentColor = Color.DarkGray
                    ),
                ) {
                    Text(
                        text = "expand"
                    )
                }

                Button(
                    onClick = {
                        isMoved = !isMoved
                        if (isMoved) {
                            offsetX = 150.dp
                            offsetY = 150.dp
                        } else {
                            offsetX = 0.dp
                            offsetY = 0.dp
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Blue,
                        contentColor = Color.White,
                        disabledContainerColor = Color.Gray,
                        disabledContentColor = Color.DarkGray
                    ),
                ) {
                    Text(
                        text = "move"
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun MainPreview() {
    MainScreen()
}