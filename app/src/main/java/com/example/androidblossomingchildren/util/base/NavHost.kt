package com.example.androidblossomingchildren.util.base

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.androidblossomingchildren.ui.presentation.AccountDeletionScreen
import com.example.androidblossomingchildren.ui.presentation.AccountFindScreen
import com.example.androidblossomingchildren.ui.presentation.HomeScreen
import com.example.androidblossomingchildren.ui.presentation.LoginScreen
import com.example.androidblossomingchildren.ui.presentation.MyPageScreen
import com.example.androidblossomingchildren.ui.presentation.OnBoardingScreen
import com.example.androidblossomingchildren.ui.presentation.PasswordResetScreen
import com.example.androidblossomingchildren.ui.presentation.SignUpCompleteScreen
import com.example.androidblossomingchildren.ui.presentation.SignUpNicknameScreen
import com.example.androidblossomingchildren.ui.presentation.SignUpPasswordScreen
import com.example.androidblossomingchildren.ui.presentation.SignUpScreen
import com.example.androidblossomingchildren.ui.presentation.SignUpVerificationScreen
import com.example.androidblossomingchildren.ui.presentation.StampScreen
import com.example.androidblossomingchildren.ui.presentation.VideoDetailScreen
import com.example.androidblossomingchildren.ui.presentation.VideoResultScreen
import com.example.androidblossomingchildren.ui.presentation.VideoScreen
import com.example.androidblossomingchildren.ui.presentation.WelcomeScreen
import com.example.androidblossomingchildren.ui.viewmodel.SignUpViewModel

@Composable
fun TionNavigationGraph(
    navController: NavHostController = rememberNavController(),
) {
    val signUpViewModel: SignUpViewModel = viewModel()

    val isFirstLogin = true /* TODO: 수정 필요. 온보딩은 그대로 두고 로그인 화면에서 자동 로그인을 처리 */

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
                onNavigateToLogin = { navController.navigateSingleTopTo(Destinations.Login.route) },
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
            MyPageScreen(
                onNavigateToDetail = { videoId ->
                    navController.navigateToSingleVideo(navController, videoId.toString())
                },
                onNavigateToAccountDelete = { navController.navigate(Destinations.AccountDelete.route) },
                navController = navController,
            )
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
            route = "${Destinations.Video.route}/{videoId}",
            arguments = listOf(
                navArgument("videoId") {
                    type = NavType.StringType
                },
            ),
        ) { backStackEntry ->
            VideoDetailScreen(
                backStackEntry.arguments?.getString("videoId").toString(),
                onNavigateToResult = { videoId ->
                    navController.navigate("${Destinations.Video.route}/$videoId/result") { launchSingleTop = true }
                },
                onNavigateToBack = { navController.popBackStack() },
            )
        }
        composable(
            route = "${Destinations.Video.route}/{videoId}/result",
            arguments = listOf(
                navArgument("videoId") {
                    type = NavType.StringType
                },
            ),
        ) { backStackEntry ->
            VideoResultScreen(
                backStackEntry.arguments?.getString("videoId").toString(),
                onNavigateToStamp = { navController.navigateSingleTopTo(Destinations.Stamp.route) },
                onNavigateToBack = { navController.popBackStack() },
            )
        }
        composable(Destinations.Login.route) {
            LoginScreen(
                onNavigateToHome = { navController.navigateSingleTopTo(Destinations.Home.route) },
                onNavigateToAccountFind = { navController.navigate(Destinations.AccountFind.route) },
                onNavigateToSignUp = { navController.navigate(Destinations.SignUp.route) },
            )
        }
        composable(Destinations.SignUp.route) {
            SignUpScreen(
                viewModel = signUpViewModel,
                onNavigateToSignUpVerification = { navController.navigate(Destinations.SignUpVerification.route) },
            )
        }
        composable(Destinations.SignUpVerification.route) {
            SignUpVerificationScreen(
                onNavigateToSignUpNickname = { navController.navigate(Destinations.SignUpNickname.route) },
            )
        }
        composable(Destinations.SignUpNickname.route) {
            SignUpNicknameScreen(
                viewModel = signUpViewModel,
                onNavigateToSignUpPassword = { navController.navigate(Destinations.SignUpPassword.route) },
            )
        }
        composable(Destinations.SignUpPassword.route) {
            SignUpPasswordScreen(
                viewModel = signUpViewModel,
                onNavigateToSignUpComplete = {
                    navController.navigateSingleTopTo(Destinations.SignUpComplete.route)
                },
            )
        }
        composable(Destinations.SignUpComplete.route) {
            SignUpCompleteScreen(
                onNavigateToLogin = { navController.navigateSingleTopTo(Destinations.Login.route) },
            )
        }
        composable(Destinations.AccountFind.route) {
            AccountFindScreen(
                onNavigateToPasswordReset = { navController.navigate(Destinations.PasswordReset.route) },
                onNavigateToBack = { navController.popBackStack() },
            )
        }
        composable(Destinations.PasswordReset.route) {
            PasswordResetScreen(
                onNavigateToLogin = { navController.navigateSingleTopTo(Destinations.Login.route) },
                onNavigateToBack = { navController.popBackStack() },
            )
        }
        composable(Destinations.AccountDelete.route) {
            AccountDeletionScreen(
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
