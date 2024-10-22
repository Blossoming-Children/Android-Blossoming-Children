@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.androidblossomingchildren.ui.presentation

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.androidblossomingchildren.R
import com.example.androidblossomingchildren.ui.components.BottomSheetNameDialog
import com.example.androidblossomingchildren.util.base.Destinations
import com.example.androidblossomingchildren.util.component.TionNavigationBar
import com.example.androidblossomingchildren.util.component.TionNavigationBarContent
import com.example.androidblossomingchildren.util.component.TionTopAppBar

@Composable
fun StampScreen(
    navController: NavHostController,
) {
    val showBottomSheet = remember { mutableStateOf(false) }
    val stampCount by remember { mutableStateOf(3) }
    val goalList = remember { mutableStateListOf("목표 설정1", "목표 설정2", "목표 설정3", "목표 설정4", "목표 설정5") }
    var isItem4 = false
    var isItem8 = false
    var isItem12 = false
    var isItem16 = false
    var isItem20 = false

    Scaffold(
        topBar = {
            TionTopAppBar(
                title = {
                    Text(
                        text = Destinations.Stamp.route,
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
                    Destinations.Stamp,
                    navController,
                )
            }
        },
    ) { padding ->
        val itemList = List(20) { it + 1 }

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            if (showBottomSheet.value && isItem4) {
                BottomSheetNameDialog(
                    onDismissRequest = {
                        showBottomSheet.value = false
                        isItem4 = false
                    },
                    nickname = goalList[0],
                    onNicknameChange = { newGoal -> goalList[0] = newGoal },
                )
            } else if (showBottomSheet.value && isItem8) {
                BottomSheetNameDialog(
                    onDismissRequest = {
                        showBottomSheet.value = false
                        isItem8 = false
                    },
                    nickname = goalList[1],
                    onNicknameChange = { newGoal -> goalList[1] = newGoal },
                )
            } else if (showBottomSheet.value && isItem12) {
                BottomSheetNameDialog(
                    onDismissRequest = {
                        showBottomSheet.value = false
                        isItem12 = false
                    },
                    nickname = goalList[2],
                    onNicknameChange = { newGoal -> goalList[2] = newGoal },
                )
            } else if (showBottomSheet.value && isItem16) {
                BottomSheetNameDialog(
                    onDismissRequest = {
                        showBottomSheet.value = false
                        isItem16 = false
                    },
                    nickname = goalList[3],
                    onNicknameChange = { newGoal -> goalList[3] = newGoal },
                )
            } else if (showBottomSheet.value && isItem20) {
                BottomSheetNameDialog(
                    onDismissRequest = {
                        showBottomSheet.value = false
                        isItem20 = false
                    },
                    nickname = goalList[4],
                    onNicknameChange = { newGoal -> goalList[4] = newGoal },
                )
            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                contentPadding = PaddingValues(all = 16.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                state = rememberLazyGridState(),
            ) {
                items(itemList) { item ->
                    if (item % 4 == 0) {
                        Card(
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                            border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary),
                            modifier = Modifier
                                .padding(4.dp)
                                .fillMaxSize()
                                .clickable {
                                    Log.d("SHEET", item.toString())
                                    showBottomSheet.value = true
                                    when (item) {
                                        4 -> {
                                            isItem4 = true
                                            Log.d("SHEET", "four!")
                                        }

                                        8 -> {
                                            isItem8 = true
                                        }

                                        12 -> {
                                            isItem12 = true
                                        }

                                        16 -> {
                                            isItem16 = true
                                        }

                                        20 -> {
                                            isItem20 = true
                                        }
                                    }
                                },
                        ) {
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .padding(8.dp)
                                    .height(80.dp),
                            ) {
                                if (stampCount >= item) {
                                    Image(
                                        painter = painterResource(id = R.drawable.img_stamp),
                                        contentDescription = "Stamp",
                                        modifier = Modifier.size(128.dp),
                                    )
                                }
                                Text(
                                    text = goalList[item / 4 - 1],
                                    modifier = Modifier.fillMaxWidth(),
                                    textAlign = TextAlign.Center,
                                )
                            }
                        }
                    } else {
                        Card(
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                            border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary),
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxSize(),
                        ) {
                            Column(
                                verticalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier
                                    .padding(8.dp)
                                    .height(80.dp),
                            ) {
                                if (stampCount >= item) {
                                    Image(
                                        painter = painterResource(id = R.drawable.img_stamp),
                                        contentDescription = "Stamp",
                                        modifier = Modifier.size(128.dp),
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
