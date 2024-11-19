package com.example.androidblossomingchildren.ui.config

fun checkPasswordForm(password: String): Boolean {
    // 길이가 10자 이상인지 확인
    if (password.length < 10) {
        return false
    }

    // 영문이 하나 이상 포함되어 있는지 확인
    val containsLetter = password.any { it.isLetter() }

    // 숫자가 하나 이상 포함되어 있는지 확인
    val containsDigit = password.any { it.isDigit() }

    // 영문과 숫자가 모두 포함되어 있는 경우에만 true 반환
    return containsLetter && containsDigit
}
