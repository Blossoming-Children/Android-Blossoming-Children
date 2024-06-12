@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.androidblossomingchildren.ui.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.androidblossomingchildren.R
import com.example.androidblossomingchildren.ui.components.BottomSheetNameDialog
import com.example.androidblossomingchildren.util.base.Destinations
import com.example.androidblossomingchildren.util.component.TionGridItem
import com.example.androidblossomingchildren.util.component.TionNavigationBar
import com.example.androidblossomingchildren.util.component.TionNavigationBarContent
import com.example.androidblossomingchildren.util.component.TionTopAppBar

@Composable
fun MyPageScreen(
    navController: NavHostController,
    onNavigateToDetail: (Any?) -> Unit,
) {
    var showBottomSheet by remember { mutableStateOf(false) }
    var nickname by remember { mutableStateOf("새싹꿈나무") }

    Scaffold(
        topBar = {
            TionTopAppBar(
                title = {
                    Text(
                        text = Destinations.MyPage.route,
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 35.sp,
                        fontWeight = FontWeight.Bold,
                    )
                },
                modifier = Modifier
                    .padding(vertical = 15.dp),
            )
        },
        bottomBar = {
            TionNavigationBar {
                TionNavigationBarContent(
                    Destinations.MyPage,
                    navController,
                )
            }
        },
    ) { paddingValue ->
        val verticalScrollState = rememberScrollState()
        Column(
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValue)
                .verticalScroll(verticalScrollState),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(270.dp)
                    .background(MaterialTheme.colorScheme.primary),

            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier
                        .padding(top = 32.dp)
                        .fillMaxWidth()
                        .fillMaxHeight(),
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.sprout),
                        contentDescription = "Profile",
                        modifier = Modifier
                            .size(160.dp)
                            .weight(1f),
                    )
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 35.dp)
                            .weight(1f),
                    ) {
                        Text(
                            text = "$nickname 어린이",
                            fontSize = 24.sp,
                            color = MaterialTheme.colorScheme.surface,
                        )

                        IconButton(
                            onClick = {
                                showBottomSheet = true
                            },
                            modifier = Modifier
                                .size(30.dp)
                                .padding(start = 10.dp),
                        ) {
                            Icon(
                                painterResource(id = R.drawable.ic_pencil),
                                contentDescription = "Fix Name",
                                tint = MaterialTheme.colorScheme.surface,
                            )
                            if (showBottomSheet) {
                                BottomSheetNameDialog(
                                    onDismissRequest = { showBottomSheet = false },
                                    nickname = nickname,
                                    onNicknameChange = { newNickname -> nickname = newNickname },
                                )
                            }
                        }
                    }
                }
            }
            Text(
                text = "동작별 학습 횟수",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(
                        top = 20.dp,
                        start = 20.dp,
                    ),
            )
            Box(
                contentAlignment = Alignment.BottomCenter,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(270.dp),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.graph),
                    contentDescription = "동작별 그래프",
                    modifier = Modifier
                        .size(250.dp),
                )
            }
            Text(
                text = "북마크",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(
                        top = 20.dp,
                        start = 20.dp,
                    ),
            )

            val itemList = List(20) { "제목 ${it + 1}" }
            LazyHorizontalGrid(
                rows = GridCells.Fixed(1),
                contentPadding = PaddingValues(all = 16.dp),
                modifier = Modifier
                    .height(230.dp),
            ) {
                items(itemList) { item ->
                    TionGridItem(
                        item,
                        onClick = {
                            onNavigateToDetail(item)
                        },
                    )
                }
            }
            Text(
                text = "로그아웃",
                color = MaterialTheme.colorScheme.tertiary,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(
                        start = 25.dp,
                        top = 20.dp,
                    ),
            )
            Text(
                text = "회원 탈퇴",
                color = MaterialTheme.colorScheme.tertiary,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(
                        start = 25.dp,
                        top = 10.dp,
                        bottom = 160.dp,
                    ),
            )
        }
    }
}
