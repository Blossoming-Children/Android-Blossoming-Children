package com.example.androidblossomingchildren.libraries.network.dataClass

data class EducationDetailResponse(
    val status: Int,
    val message: String,
    val result: List<EducationDetailResult>?,
)
