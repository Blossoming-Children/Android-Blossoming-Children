package com.example.androidblossomingchildren.util.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme =
    darkColorScheme()

private val LightColorScheme =
    lightColorScheme(
        primary = Blue500,
        onPrimary = Blue200,
        primaryContainer = Blue400,
        secondary = Red500,
        onSecondary = Red200,
        secondaryContainer = Red400,
        tertiary = Black500,
        onTertiary = Black400,
        tertiaryContainer = Black600,
        error = ErrorRed700,
        onError = ErrorRed700,
        errorContainer = ErrorRed400,
        surface = White,
        onSurface = Black,
        onSurfaceVariant = Black,
    )

@Composable
fun TionTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content,
    )
}

/*
@Composable
fun AndroidBlossomingChildrenTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit,
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content,
    )
}
*/
