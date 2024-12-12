package com.example.androidblossomingchildren.util.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.LifecycleOwner
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@Composable
fun TionYouTubePlayer(
    youtubeVideoId: String,
    lifecycleOwner: LifecycleOwner,
    modifier: Modifier = Modifier,
    onVideoReady: () -> Unit = {},
    onVideoPlaying: () -> Unit,
    onVideoEnded: () -> Unit,
) {
    AndroidView(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(10.dp)),
        factory = {
            YouTubePlayerView(context = it).apply {
                lifecycleOwner.lifecycle.addObserver(this)

                addYouTubePlayerListener(
                    object : AbstractYouTubePlayerListener() {
                        override fun onReady(youTubePlayer: YouTubePlayer) {
                            youTubePlayer.loadVideo(youtubeVideoId, 0f) // 영상을 불러오고 즉시 재생
                            onVideoReady()
                        }
                        override fun onStateChange(
                            youTubePlayer: YouTubePlayer,
                            state: PlayerConstants.PlayerState,
                        ) {
                            when (state) {
                                PlayerConstants.PlayerState.PLAYING -> {
                                    // 유튜브 영상 재생이 시작되었을 때 호출
                                    onVideoPlaying()
                                }
                                PlayerConstants.PlayerState.ENDED -> {
                                    // 유튜브 영상 재생이 끝났을 때 호출
                                    onVideoEnded()
                                }

                                else -> {}
                            }
                        }
                    },
                )
            }
        },
    )
}
