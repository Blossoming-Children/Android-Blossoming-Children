@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.androidblossomingchildren.ui.presentation

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidblossomingchildren.libraries.network.dataClass.EducationResult
import com.example.androidblossomingchildren.ui.config.extractYouTubeVideoId
import com.example.androidblossomingchildren.ui.config.getEducationInfo
import com.example.androidblossomingchildren.ui.viewmodel.VideoDetailViewModel
import com.example.androidblossomingchildren.util.base.Destinations
import com.example.androidblossomingchildren.util.component.TionGridItem
import com.example.androidblossomingchildren.util.component.TionTopAppBarBack

@Composable
fun VideoScreen(
    onNavigateToDetail: (Int) -> Unit,
    onNavigateToBack: () -> Unit,
    viewModel: VideoDetailViewModel,
) {
    val context: Context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
    val authId = sharedPreferences.getLong("saved_authId", 0)
    Log.d("Login: ", "authId: $authId")
    var showToast by remember { mutableStateOf(false) }
    var toastMessage by remember { mutableStateOf("") }
    var itemList by remember { mutableStateOf<List<EducationResult>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(authId) {
        getEducationInfo(
            authId,
            onSuccess = { results ->
                itemList = results.map { result ->
                    val videoId = extractYouTubeVideoId(result.url)
                    val thumbnailUrl = "https://img.youtube.com/vi/$videoId/hqdefault.jpg"
                    EducationResult(eduId = result.eduId, title = result.title, url = thumbnailUrl, isBookmarked = result.isBookmarked, achievement = result.achievement)
                }
                isLoading = false
            },
            onFailure = { message ->
                // 실패 시 토스트 메시지 표시
                toastMessage = message
                showToast = true
                isLoading = false
            },
        )
    }

    Scaffold(
        topBar = {
            TionTopAppBarBack(
                title = {
                    Text(
                        text = Destinations.Video.route,
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                    )
                },
                modifier = Modifier
                    .padding(vertical = 15.dp),
                onNavigationClick = onNavigateToBack,
            )
        },
        content = { padding ->
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(all = 16.dp),
                modifier = Modifier.padding(padding),
            ) {
                items(itemList) { item ->
                    val videoBookmarkedState: MutableState<Boolean> = remember { mutableStateOf(item.isBookmarked) }
                    TionGridItem(
                        title = item.title,
                        imageUrl = item.url,
                        progress = item.achievement,
                        isBookmarkedState = videoBookmarkedState,
                        onClick = {
                            onNavigateToDetail(
                                item.eduId,
                            )
                        },
                        addBookmark = {
                        },
                    )
                }
            }
            if (showToast) {
                TionToast(
                    messageTxt = toastMessage,
                    onDismiss = { showToast = false },
                )
            }
        },
    )
}
