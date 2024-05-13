package com.example.androidblossomingchildren

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidblossomingchildren.ui.theme.AndroidBlossomingChildrenTheme
import com.example.androidblossomingchildren.ui.theme.Blue
import com.example.androidblossomingchildren.ui.theme.Gray
import com.example.androidblossomingchildren.ui.theme.Red
import com.example.androidblossomingchildren.ui.theme.Yellow


class StudyActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidBlossomingChildrenTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    StudyScreen(activity = this)
                }
            }
        }
    }
}


/*
@Composable
fun StudyActivity(){
    AndroidBlossomingChildrenTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
            StudyScreen()
        }
    }
}
*/


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun StudyScreen(activity: ComponentActivity) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            StudyTopAppBar(onBackClicked = { activity.finish() })
        },
        content = { padding ->
            val itemList = List(20) { "제목 ${it + 1}" }

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(all = 16.dp),
                modifier = Modifier.padding(padding),
            ) {
                items(itemList) { item ->
                    GridItem(item, onClick = {
                        Intent(context, StudyDetailActivity::class.java).apply {
                            putExtra("ITEM_TEXT", item)
                            context.startActivity(this)
                        }
                    })
                }
            }
        },
    )
}

@Composable
fun GridItem(item: String, onClick: () -> Unit) {
    val progress = 0.75f

    var isBookmarked by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp,
        ),
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Image(
                painter = painterResource(id = R.drawable.video_example),
                contentDescription = item,
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop,
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
            ) {
                LinearProgressIndicator(
                    progress = progress,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    color = Red,
                    trackColor = Color.LightGray,
                )

                Text(
                    text = "${(progress * 100).toInt()}%",
                    modifier = Modifier.padding(start = 4.dp),
                    style = MaterialTheme.typography.bodyMedium.copy(fontFamily = FontFamily(Font(R.font.laundrygothic_regular))),
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 4.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.bookmark_blank),
                    tint = if (isBookmarked) Yellow else Gray,
                    contentDescription = "Bookmark",
                    modifier = Modifier
                        .clickable { isBookmarked = !isBookmarked }
                )

                Text(
                    text = item,
                    modifier = Modifier.padding(start = 4.dp),
                    style = MaterialTheme.typography.bodyLarge.copy(fontFamily = FontFamily(Font(R.font.laundrygothic_regular))),
                    fontSize = 16.sp
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudyTopAppBar(onBackClicked: () -> Unit) {
    CenterAlignedTopAppBar(
        navigationIcon = {
            IconButton(onClick = onBackClicked) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBack,
                    contentDescription = "Back",
                )
            }
        },
        title = {
            Text(
                text = "동작 교육",
                fontFamily = FontFamily(Font(R.font.laundrygothic_bold)),
                color = Blue,
                fontSize = 20.sp,
            )
        },
    )
}
