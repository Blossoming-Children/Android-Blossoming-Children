package com.example.androidblossomingchildren.util.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme =
    darkColorScheme()

private val LightColorScheme =
    lightColorScheme(
        primary = BlueGrey500,
        onPrimary = Blue50,
        secondaryContainer = BlueGrey500,
        onSecondaryContainer = Blue80,
        tertiaryContainer = Blue80,
        onTertiaryContainer = Color.White,
        error = Color.Red,
        onError = Color.White,
        surface = Color.White,
        onSurface = Color.Black,
        onSurfaceVariant = Color.Black,
        outlineVariant = Color.Black,
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
