package com.example.androidblossomingchildren.ui.config

fun checkEmailForm(email: String): Boolean {
    val emailPattern = buildString {
        append("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")
    }
    return email.matches(Regex(emailPattern))
}
