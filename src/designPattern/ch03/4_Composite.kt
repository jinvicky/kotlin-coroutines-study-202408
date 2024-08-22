package designPattern.ch03

val bobaFett = StormTrooper(Rifle(), RegularLegs())

// val squad = Squad(listOf(bobaFett.copy(), bobaFett.copy(), bobaFett.copy()))

//class Squad(val units: List<Trooper>)
class Squad(private val units: List<Trooper>): Trooper {
    override fun move(x: Long, y: Long) {
        for (u in units) {
            u.move(x, y)
        }
    }

    override fun attackRebel(x: Long, y: Long) {
        for (u in units) {
            u.attackRebel(x, y)
        }
    }

    override fun retreat() {
        println("Shout!!!!")
    }

    constructor() : this(listOf())
    constructor(t1: Trooper) : this(listOf(t1))
    constructor(t1: Trooper, t2: Trooper) : this(listOf(t1, t2))

    constructor(vararg units: Trooper) :
            this(units.toList())

}

// 리스트로 감싸지 않고 많약에 트루퍼 객체들을 직접 전달하고 싶으면 어떻게 할 것인가?
//val squad = Squad(bobaFett.copy(), bobaFett.copy(), bobaFett.copy())

val squad2 = Squad(bobaFett.copy(), bobaFett.copy(), bobaFett.copy())
val platoon = Squad(Squad(), Squad())

