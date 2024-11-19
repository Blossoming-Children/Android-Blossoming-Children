package com.example.androidblossomingchildren.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class SignUpViewModel : ViewModel() {
    var email = mutableStateOf("")
    var name = mutableStateOf("")
    var password = mutableStateOf("")
}
