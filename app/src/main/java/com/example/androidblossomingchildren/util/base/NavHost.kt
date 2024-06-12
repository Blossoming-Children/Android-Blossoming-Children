package com.example.androidblossomingchildren.util.base

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.androidblossomingchildren.ui.presentation.HomeScreen
import com.example.androidblossomingchildren.ui.presentation.MyPageScreen
import com.example.androidblossomingchildren.ui.presentation.OnBoardingScreen
import com.example.androidblossomingchildren.ui.presentation.StampScreen
import com.example.androidblossomingchildren.ui.presentation.VideoDetailScreen
import com.example.androidblossomingchildren.ui.presentation.VideoScreen
import com.example.androidblossomingchildren.ui.presentation.WelcomeScreen

@Composable
fun TionNavigationGraph(
    navController: NavHostController = rememberNavController(),
) {
    val isFirstLogin = true
    NavHost(
        navController = navController,
        startDestination = Destinations.OnBoarding.route,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { ExitTransition.None },
    ) {
        composable(Destinations.OnBoarding.route) {
            OnBoardingScreen(
                onNavigateToApp = {
                    if (isFirstLogin) {
                        navController.navigateSingleTopTo(Destinations.Welcome.route)
                    } else {
                        navController.navigateSingleTopTo(Destinations.Home.route)
                    }
                },
            )
        }
        composable(Destinations.Welcome.route) {
            WelcomeScreen(
                onNavigateToHome = { navController.navigateSingleTopTo(Destinations.Home.route) },
            )
        }
        composable(Destinations.Home.route) {
            HomeScreen(
                navController = navController,
                onNavigateToVideo = { navController.navigate(Destinations.Video.route) },
            )
        }
        composable(Destinations.Stamp.route) {
            StampScreen(navController)
        }
        composable(Destinations.MyPage.route) {
            MyPageScreen(navController)
        }
        composable(Destinations.Video.route) {
            VideoScreen(
                onNavigateToDetail = { videoId ->
                    navController.navigateToSingleVideo(navController, videoId.toString())
                },
                onNavigateToBack = { navController.popBackStack() },
            )
        }
        composable(
            route = "video/{videoId}",
            arguments = listOf(
                navArgument("videoId") {
                    type = NavType.StringType
                },
            ),
        ) { backStackEntry ->
            VideoDetailScreen(
                backStackEntry.arguments?.getString("videoId").toString(),
                onNavigateToBack = { navController.popBackStack() },
            )
        }
    }
}

fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        popUpTo(this@navigateSingleTopTo.graph.id) {
            inclusive = true
        }
        launchSingleTop = true
        restoreState = false
    }

private fun NavHostController.navigateToSingleVideo(
    navController: NavHostController,
    videoId: String,
) {
    navController.navigate("${Destinations.Video.route}/$videoId") { launchSingleTop = true }
}
