package com.example.androidblossomingchildren.libraries.network.dataClass

data class EducationResult(
    val eduId: Int,
    val title: String,
    val url: String,
    val isBookmarked: Boolean = false,
    val achievement: Int = 0,
)
