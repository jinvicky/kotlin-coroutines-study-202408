package ch03

import kotlin.coroutines.suspendCoroutine

suspend fun main() {
    println("Before")

//    suspendCoroutine<Unit> {  }
    suspendCoroutine<Unit> { continuation ->
        println("Before too")
    }
    // 인자로 들어간 람다 함수는 중단되기 전에 실행된다. 컨티뉴에이션 객체를 인자로 받는다.

    println("After")
}