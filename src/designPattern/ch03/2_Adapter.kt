package designPattern.ch03

import java.util.stream.Stream

interface USPlug {
    val hasPower: Int
}

interface EUPlug {
    val hasPower: String // "YES" or "NO"
}

interface UsbMini {
    val hasPower: PowerState
}

enum class PowerState {
    TRUE, FALSE
}

interface UsbTypeC {
    val hasPower: Boolean
}

fun cellPhone(chargeCable: UsbTypeC) {
    if (chargeCable.hasPower) {
        println("I've Got The Power!")
    } else {
        println("No power")
    }
}

fun charger(plug: EUPlug): UsbMini {
    return object : UsbMini {
        override val hasPower = if (plug.hasPower == "YES")
            PowerState.TRUE else PowerState.FALSE
    }
}

fun usPowerOutlet(): USPlug {
    return object : USPlug {
        override val hasPower = 1
    }
}

fun UsbMini.toUsbTypeC(): UsbTypeC {
    val hasPower = this.hasPower == PowerState.TRUE
    return object : UsbTypeC {
        override val hasPower = hasPower
    }
}

fun USPlug.toEUPlug(): EUPlug {
    val hasPower = if (this.hasPower == 1) "YES" else "NO"
    return object : EUPlug {
        override val hasPower = hasPower
    }
}

fun <T> streamProcessing(stream: Stream<T>) {
}

fun <T> collectionProcessing(c: Collection<T>) {
    for(e in c) {
        println(e)
    }
}

fun main() {
//    cellPhone(
//        charger(
//            usPowerOutlet()
//        )
//    )

    cellPhone(
        charger (
            usPowerOutlet().toEUPlug()
        ).toUsbTypeC()
    )

    val l = listOf("a", "b", "c")

    streamProcessing(l.stream())

    val s = (Stream.generate { 42 }).toList() // 힙 영역 메모리를 벗어나서 예외 발생
    println(s)

    // collectionProcessing(s.toList()); // 어댑터를 무한루프에 빠지는 방식으로 쓰지 마라.
}

