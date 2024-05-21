package com.example.androidblossomingchildren

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.androidblossomingchildren.util.theme.TionTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TionTheme {
                TionNavigationGraph()
            }
        }
    }
}
