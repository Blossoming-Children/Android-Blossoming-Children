package com.example.androidblossomingchildren.libraries.network.dataClass

import com.google.gson.annotations.SerializedName

data class ClassificationResponse(
    @SerializedName("class") val classification: String,
)
