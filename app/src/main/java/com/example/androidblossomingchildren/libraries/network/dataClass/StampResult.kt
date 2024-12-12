package com.example.androidblossomingchildren.libraries.network.dataClass

data class StampResult(
    val userId: Long,
    val stampCount: Int,
    val goals: List<StampGoal>,
)
