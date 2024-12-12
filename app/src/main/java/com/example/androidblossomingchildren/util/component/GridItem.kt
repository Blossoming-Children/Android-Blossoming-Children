package com.example.androidblossomingchildren.util.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.androidblossomingchildren.R
import com.example.androidblossomingchildren.util.theme.Black300
import com.example.androidblossomingchildren.util.theme.Yellow500

@Composable
fun TionGridItem(
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    progress: Int = 0,
    isBookmarkedState: MutableState<Boolean>,
    imageUrl: String,
    bookmarkIconResource: Int = R.drawable.bookmark_blank,
    progressColor: Color = MaterialTheme.colorScheme.secondary,
    trackColor: Color = Color.LightGray,
    bookmarkColor: Color = Yellow500,
    nonBookmarkColor: Color = Black300,
    addBookmark: () -> Unit,
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        modifier = modifier
            .padding(8.dp)
            .fillMaxSize()
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Image(
                painter = rememberImagePainter(data = imageUrl),
                contentDescription = title,
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp),
                contentScale = ContentScale.Crop,
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
            ) {
                LinearProgressIndicator(
                    progress = (progress.toFloat() / 100),
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    color = progressColor,
                    trackColor = trackColor,
                )

                Text(
                    text = "$progress%",
                    modifier = Modifier.padding(start = 4.dp),
                    style = MaterialTheme.typography.bodyMedium.copy(fontFamily = FontFamily(Font(R.font.laundrygothic_regular))),
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 4.dp),
            ) {
                Icon(
                    painter = painterResource(id = bookmarkIconResource),
                    tint = if (isBookmarkedState.value) bookmarkColor else nonBookmarkColor,
                    contentDescription = "Bookmark",
                    modifier = Modifier
                        .clickable {
                            isBookmarkedState.value = !isBookmarkedState.value
                            addBookmark()
                        },
                )

                Text(
                    text = title,
                    modifier = Modifier.padding(start = 4.dp),
                    style = MaterialTheme.typography.bodyLarge.copy(fontFamily = FontFamily(Font(R.font.laundrygothic_regular))),
                    fontSize = 16.sp,
                )
            }
        }
    }
}
