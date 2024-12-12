package com.example.androidblossomingchildren.ui.config

// 유튜브 URL에서 Video ID를 추출하는 함수
fun extractYouTubeVideoId(url: String): String? {
    val regex = "v=([a-zA-Z0-9_-]+)".toRegex()
    val matchResult = regex.find(url)
    return matchResult?.groups?.get(1)?.value
}
