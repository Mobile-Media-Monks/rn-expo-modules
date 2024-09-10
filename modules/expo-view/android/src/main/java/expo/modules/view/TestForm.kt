package expo.modules.view

import android.R.color
import android.annotation.SuppressLint
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.util.lerp
import kotlin.math.roundToInt


@SuppressLint("UnrememberedMutableState")
@Composable
fun TestForm() {
    val width = LocalConfiguration.current.screenWidthDp.dp
    var offsetX by remember { mutableFloatStateOf(0f) }

    val totalScrollRange = with(LocalDensity.current) { (width.toPx() - 80.dp.toPx()) }
    val phase1End = totalScrollRange / 2
    val phase2End = 3 * totalScrollRange / 3


    val boxSize by derivedStateOf {
        when {
            offsetX < phase1End -> {
                val progress = offsetX / phase1End
                lerp(40.dp, 40.dp, progress) to lerp(40.dp, 100.dp, progress)
            }

            offsetX < phase2End -> {
                val progress = (offsetX - phase1End) / (phase2End - phase1End)
                lerp(40.dp, 150.dp, progress) to lerp(100.dp, 150.dp, progress)
            }

            else -> 150.dp to 150.dp
        }
    }


    val rotateBox by derivedStateOf {
        when {
            offsetX < phase1End -> {
                val progress = offsetX / phase1End
                lerp(0f, 90f, progress) to lerp(0f, -90f, progress)
            }

            offsetX < phase2End -> {
                val progress = (offsetX - phase1End) / (phase2End - phase1End)

                lerp(90f, 360f, progress) to lerp(-90f, -360f, progress)
            }

            else -> 360f to -360f
        }
    }


    Column(
        Modifier
            .background(Color.White)
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(horizontal = 20.dp)
            .draggable(
                orientation = Orientation.Horizontal,
                state = rememberDraggableState { delta ->
                    offsetX = (offsetX + delta).coerceIn(0f, totalScrollRange)
                }), verticalArrangement = Arrangement.Center
    ) {

        Column(
            modifier = Modifier
                .height(400.dp)
                .fillMaxWidth()
                .animateContentSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .animateContentSize(),
//                verticalAlignment = Alignment.CenterVertically,
//                horizontalArrangement = Arrangement.Center
//            ) {
//
//                Box(
//                    Modifier
//                        .width(boxSize.first)
//                        .height(boxSize.second)
//                        .rotate(rotateBox.first)
//                        .clip(CircleShape)
//                        .animateContentSize()
//                        .background(Color.Black)
//                )
//
//                Spacer(modifier = Modifier.width(20.dp))
//
//                Box(
//                    Modifier
//                        .width(boxSize.first)
//                        .height(boxSize.second)
//                        .rotate(rotateBox.second)
//                        .clip(CircleShape)
//                        .animateContentSize()
//                        .background(Color.Black)
//                )
//
//            }

            Canvas(modifier = Modifier.fillMaxWidth()) {
                val path = Path().apply {
                    


                    close()
                }

                drawPath(
                    path = path,
                    color = Color.Black,
                    style = Fill
                )
            }

            Spacer(modifier = Modifier
                .height(100.dp)
                .width(300.dp)
                .drawWithContent {
                    drawArc(
                        color = Color.Black,
                        topLeft = Offset(size.width * 0.43f, size.height * 0.0f),
                        startAngle = 0f,
                        sweepAngle = 180f,
                        useCenter = false,
                        style = Stroke(width = size.width * 0.03f),
                        size = Size(100f, 100f)
                    )
                }
            )
        }

        Box {
            Box(
                modifier = Modifier
                    .height(10.dp)
                    .fillMaxWidth()
                    .background(Color.Gray.copy(alpha = 0.9f))
                    .align(Alignment.Center)
            )
            Row(
                Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                repeat(3) {
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(30.dp)
                            .background(Color.Gray)
                    )
                }
            }

            Box(modifier = Modifier
                .offset { IntOffset(offsetX.roundToInt(), 0) }
                .clip(CircleShape)
                .size(40.dp)
                .background(Color.Black))

        }
    }
}


@Preview
@Composable
fun TestFormPreview() {
    TestForm()
}