package com.example.androidblossomingchildren

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidblossomingchildren.components.TionGrid
import com.example.androidblossomingchildren.ui.theme.AndroidBlossomingChildrenTheme
import com.example.androidblossomingchildren.ui.theme.Blue


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
                    TionGrid(
                        item,
                        onClick = {
                            Intent(context, StudyDetailActivity::class.java).apply {
                                putExtra("ITEM_TEXT", item)
                                context.startActivity(this)
                            }
                        },
                    )
                }
            }
        },
    )
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
