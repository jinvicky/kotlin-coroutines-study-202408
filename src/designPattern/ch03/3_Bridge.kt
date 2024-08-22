package designPattern.ch03

interface Trooper {
    fun move(x: Long, y: Long)
    fun attackRebel(x: Long, y: Long)

    fun retreat() {
        println("Retreating!")
    }
}
//
//// final이다.
//open class StormTrooper : Trooper {
//    override fun move(x: Long, y: Long) {
//        // 보통 속도로 이동
//    }
//
//    override fun attackRebel(x: Long, y: Long) {
//        // 대부분 빗나감
//    }
//}
//
//open class ShockTrooper : Trooper {
//    override fun move(x: Long, y: Long) {
//        // 일반 StormTropper보다는 느리게 이동
//    }
//
//    override fun attackRebel(x: Long, y: Long) {
//        // 명중할 때가 있음
//    }
//}
//
//// 더 강한 버전
//class RiotControlTrooper : StormTrooper() {
//    override fun attackRebel(x: Long, y: Long) {
//        // 전기 충격 곤봉을 가졌다.
//    }
//}
//
//class FlameTrooper : ShockTrooper() {
//    override fun attackRebel(x: Long, y: Long) {
//        // 화염방사기를 사용한다. 위험!
//    }
//}
//
//class ScoutTrooper : ShockTrooper() {
//    override fun move(x: Long, y: Long) {
//
//    }
//}
//
//// trooper에 소리지르기 기능을 추가한 인터페이스를 선언한다.
//interface Infantry {
//    fun move(x: Long, y: Long)
//    fun attackRebel(x: Long, y: Long)
//    fun shout(): String
//}


// 상속을 사용하지 않고 이제 생성자로 전달해보자. 주입받는 속성도 인터페이스여야만 나중에 어떤 속성을 사용할 지 결정할 수 있다.
data class StormTrooper(
    private val weapon: Weapon,
    private val legs: Legs
) : Trooper {
    override fun move(x: Long, y: Long) {
        legs.move(x,y)
    }

    override fun attackRebel(x: Long, y: Long) {
        weapon.attack(x,y)
    }
}

typealias PointsOfDamage = Long
typealias Meters = Int
interface Weapon {
    fun attack(x: Long, y: Long): PointsOfDamage
}

interface Legs {
    fun move(x: Long, y: Long): Meters
}

const val RIFLE_DAMAGE = 3L
const val REGULAR_SPEED: Meters = 1

class Rifle : Weapon {
    override fun attack(x: Long, y: Long) = RIFLE_DAMAGE
}
class Flamethrower : Weapon {
    override fun attack(x: Long, y: Long) = RIFLE_DAMAGE * 2
}

class Batton : Weapon {
    override fun attack(x: Long, y: Long) = RIFLE_DAMAGE * 3
}

class RegularLegs : Legs {
    override fun move(x: Long, y: Long) = REGULAR_SPEED
}
class AthleticLegs : Legs {
    override fun move(x: Long, y: Long) = REGULAR_SPEED * 2
}

fun main() {
    val stormTrooper = StormTrooper(Rifle(), RegularLegs())
    val flameTrooper = StormTrooper(Flamethrower(), RegularLegs())
    val scoutTrooper = StormTrooper(Rifle(), AthleticLegs())

    println(listOf(stormTrooper, flameTrooper, scoutTrooper))
}

