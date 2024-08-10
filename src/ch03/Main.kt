package ch03

import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

fun continueAfterSecond(continuation: Continuation<Unit>) { // 정해진 시간 뒤에 코루틴을 다시 재개하는 함수, 람다표현식으로 컨티뉴에이션 객체를 통제한다.
    thread {
        Thread.sleep(1000)
        continuation.resume(Unit) // resume에 왜 Unit을 넣을까?
    }
}

private val executor =
    Executors.newSingleThreadScheduledExecutor { // jvm이 제공하는 SheduledExecutorService를 사용할 수 있다.
        Thread(it, "scheduler").apply { isDaemon = true }
    }

class MyException: Throwable("Just an exception")

suspend fun main() {
    println("Before")

//    suspendCoroutine<Unit> {  }

//    suspendCoroutine<Unit> { continuation ->
//        println("Before too")
//    }

//    suspendCoroutine<Unit> { continuation ->
//        continuation.resume(Unit); // resume()을 호출했기에 println("After")가 실행될 수 있다.
//    }

//    suspendCoroutine<Unit> { continuation  ->
//      thread {
//          println("Suspended")
//          Thread.sleep(1000) // 잠깐 재우고 재개되는 다른 스레드를 실행할 수도 있다.
//          continuation.resume(Unit)
//          println("Resumed") // After 출력 후에 Resumed가 출력된다.
//      }
//    }

//    suspendCoroutine<Unit> { continuation ->
//        continueAfterSecond(continuation)
//    }

//    suspendCoroutine<Unit> { continuation ->
//        executor.schedule({
//            continuation.resume(Unit)
//        }, 1000, TimeUnit.MILLISECONDS)
//    }

//    val ret: Unit =
//        suspendCoroutine<Unit> { cont: Continuation<Unit> ->
//            cont.resume(Unit) // Unit은 함수의 리턴 타입이면서 + Continuation의0 제네릭 타입 인자이기도 하다.
//        }
//
//    val i: Int = suspendCoroutine<Int> { cont ->
//        cont.resume(42)
//    }
//    println(i)
//
//    val str: String = suspendCoroutine<String> { cont ->
//        cont.resume("Some text")
//    }
//    println(str)
//
//    val b: Boolean = suspendCoroutine<Boolean> { cont ->
//        cont.resume(true)
//    }
//    println(b)

    try {
        suspendCoroutine<Unit> { cont ->
            // requestUser {} 와 같은 action을 수행한다.
            cont.resumeWithException(MyException()) // 네트워크 관련 예외를 알릴 때 사용가능하다.
        }
    } catch (e: MyException) {
        println("Caught!")
    }

    println("After")
}