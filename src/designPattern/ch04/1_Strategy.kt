package designPattern.ch04

enum class Direction {
    LEFT, RIGHT
}

data class Projectile(
    private var x: Int,
    private var y: Int,
    private var direction: Direction
)

interface Weapon {
    fun shoot(
        x: Int,
        y: Int,
        direction: Direction
    ): Projectile
}

/**
 * 각 무기를 별도의 클래스가 아닌 함수로 정의해서 object를 사용한 네임스페이스에 넣을 수 있으니 얼마나 많은 클래스를 절약할 수 있는가!
 * 이때는 이거, 저때는 저거, 그럴때는 그거 식으로 원할 때마다 골라서 호출할 수 있다.
 */
object Weapons {
    fun peashooter(x: Int, y: Int, direction: Direction): Projectile {
        println("It's a peashooter")
        return Projectile(x,y,direction)
    }

    fun banana(x: Int, y: Int, direction: Direction): Projectile {
        println("It's a banana")
        return Projectile(x,y, direction)
    }

    fun pomegranate(x: Int, y: Int, direction: Direction): Projectile {
        println("It's a pomegranate")
        return Projectile(x,y,direction)
    }
}

class OurHero {
    private var direction = Direction.LEFT
    private var x: Int = 42
    private var y: Int = 173

    // 이거 생성자야? 기본이 콩이라서 바나나 슛해도 콩 나오고 바나나 나오는 거?
    var currentWeapon = Weapons::peashooter

    val shoot = fun() {
        currentWeapon(x,y,direction)
    }
}

fun main() {
    val hero = OurHero()
    hero.shoot()
    hero.currentWeapon = Weapons::banana
    hero.shoot()
}