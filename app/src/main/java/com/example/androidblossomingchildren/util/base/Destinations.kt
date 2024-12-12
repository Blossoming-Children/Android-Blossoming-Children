package com.example.androidblossomingchildren.util.base

enum class Destinations(
    val route: String,
) {
    OnBoarding(
        route = "OnBoarding",
    ),
    Welcome(
        route = "Welcome",
    ),
    Home(
        route = "홈",
    ),
    Stamp(
        route = "도장판",
    ),
    MyPage(
        route = "마이페이지",
    ),
    Video(
        route = "동작 교육",
    ),
    Login(
        route = "로그인",
    ),
    SignUp(
        route = "회원가입",
    ),
    SignUpVerification(
        route = "회원가입 이메일",
    ),
    SignUpPassword(
        route = "회원가입 비밀번호",
    ),
    SignUpComplete(
        route = "회원가입 완료",
    ),
    AccountFind(
        route = "계정 찾기",
    ),
    PasswordReset(
        route = "비밀번호 재설정",
    ),
    AccountDelete(
        route = "회원 탈퇴",
    ),
}
