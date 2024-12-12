package com.example.androidblossomingchildren.ui.config

import java.util.UUID

fun generateAccessToken(): String {
    return UUID.randomUUID().toString()
}
