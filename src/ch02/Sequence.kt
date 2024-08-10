package ch02

import java.math.BigInteger
import kotlin.random.Random

/**
 * 시퀀스 기본 사용
 */
val seq = sequence {
    yield(1);
    yield(2);
    yield(3);
}

/**
 * 시퀀스를 사용한 피보나치
 */
val fibonacci: Sequence<BigInteger> = sequence {
    var first = 0.toBigInteger()
    var second = 1.toBigInteger()
    while(true) {
        yield(first)
        val temp = first
        first += second
        second = temp
    }
}

/**
 * 시퀀스 빌더를 사용한 난수, 랜덤 문자열 생성
 */
fun randomNumbers(
    seed: Long = System.currentTimeMillis()
): Sequence<Int> = sequence {
    val random = Random(seed)
    while(true) {
        yield(random.nextInt())
    }
}

fun randomUniqueStrings(
    length: Int,
    seed: Long = System.currentTimeMillis()
): Sequence<String> = sequence {
    val random = Random(seed)
    val charPool = ('a'..'z') + ('A'..'Z') + ('0'..'9')
    while(true) {
        val randomString = (1..length)
            .map { i -> random.nextInt(charPool.size) }
            .map(charPool::get)
            .joinToString("")
        yield(randomString)
    }
}.distinct()

/**
 * 시퀀스 빌더는 중단 함수를 사용하면 안된다. 실제로 중단 연산을 호출할 수 없다.
 * 중단 함수를 쓰고 싶다면 flow를 사용한다.
 */
//fun allUsersFlow(
//    api: UserApi
//): Flow<User> = flow {
//    var page = 0;
//    do {
//        val users = api.takePage(page++)
//        emitAll(users)
//    } while (!users.isNullOrEmpty())
//}

fun main() {
//    for (num in seq) {
//        print(num)
//    }

    print(fibonacci.take(10).toList())
}