package com.farimarwat.loom.core.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.farimarwat.loom.core.media.VideoPlayerState
import com.farimarwat.loom.utils.getPlatformWindowSize
import loomvideoplayer.composeapp.generated.resources.Res
import loomvideoplayer.composeapp.generated.resources.ic_fullscreen
import org.jetbrains.compose.resources.painterResource

@Composable
fun MediaControls(
    state:VideoPlayerState
) {
    var showControls by remember { mutableStateOf(true) }
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                showControls = !showControls
            }
    ) {
        val screen = getPlatformWindowSize()
        var sliderPosition by remember { mutableStateOf(0f) }
        AnimatedVisibility(
            visible = showControls,
            enter = fadeIn(),
            exit = fadeOut()
        ){
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ){
                //Play Button
                IconButton(
                    modifier = Modifier
                        .size(screen.width.times(0.2f))
                        .align(Alignment.Center)
                        .clip(CircleShape)
                        .background(Color.Black.copy(0.5f))
                    ,
                    onClick = {
                        state.togglePlay()
                    }
                ) {
                    Icon(
                        modifier = Modifier
                            .size(screen.width.times(0.15f)),
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = "Toggle Play",
                        tint = Color.White
                    )
                }

                //BottomBar
                Row (
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .background(Color.Black.copy(0.5f))
                        .padding(horizontal = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ){

                    //Time
                    Row (
                        modifier = Modifier
                            .background(Color.White.copy(0.5f))
                            .padding(vertical = 4.dp, horizontal = 8.dp)
                        ,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ){
                        //Current Position
                        val textColor = Color.Black
                        Text(
                            text = "0:22",
                            style = MaterialTheme.typography.labelMedium,
                            color = textColor
                        )
                        //Total Duration
                        Text(
                            text = "30:00",
                            style = MaterialTheme.typography.labelMedium,
                            color = textColor
                        )
                    }


                    //Slider
                    Slider(
                        value = sliderPosition,
                        onValueChange = {sliderPosition = it},
                        valueRange = 0f..100f,
                        steps = 100,
                        modifier = Modifier
                            .weight(1f)
                    )
                    IconButton(
                        modifier = Modifier
                            .size(screen.width.times(0.05f)),
                        onClick = {}
                    ){
                        Icon(
                            painter = painterResource(Res.drawable.ic_fullscreen),
                            contentDescription = "FullScreen",
                            tint = Color.White
                        )
                    }

                }
            }
        }
    }
}