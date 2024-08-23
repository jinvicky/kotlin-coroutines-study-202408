package designPattern.ch04

fun main() {
    val t = Trooper()

    t.addOrder(moveGenerator(t, 1, 1))
    t.addOrder(moveGenerator(t, 2, 2))
    t.addOrder(moveGenerator(t, 3, 3))

    t.executeOrders()

    t.appendMove(0,4)
    .appendMove(5,4)
    .appendMove(5,8)
    .appendMove(10,8)
    .executeOrders()

}
open class Trooper {
    private val orders = mutableListOf<Command>()
    private val undoableOrders = mutableListOf<Pair<Command, Command>>()

    fun addOrder(order: Command) {
        this.orders.add(order)
    }

    fun executeOrders() {
        while(orders.isNotEmpty()) {
            val order = orders.removeFirst()
            order()
        }
    }

    fun appendMove(x: Int, y: Int): Trooper = apply {

        orders.add(moveGenerator(this, x, y))

        undoableOrders.add(moveGenerator(this, x,y) to moveGenerator(this, -x, -y))
    }

    // x,y는 저장 안하나?
    fun move(x: Int, y: Int) {
        println("Moving to $x: $y")
    }
}

typealias Command = () -> Unit

// 클래스를 Trooper를 만들었는데 실제로 움직이는 것은 일급 객체다.
val moveGenerator = fun(
    t: Trooper,
    x: Int,
    y: Int
): Command {
    return fun() {
        t.move(x,y)
    }
}