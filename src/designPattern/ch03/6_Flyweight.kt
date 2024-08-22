package designPattern.ch03

import java.io.File

enum class Direction {
    LEFT,
    RIGHT
}

class TansanianSnail {
    val directionFacing = Direction.LEFT
//    val sprites = listOf(File("snail-left.jpg"),
//                         File("snail-right.jpg"))

    val sprites = List(8) { i ->
        File(when(i) {
            0 -> "snail-left.jpg"
            1 -> "snail-right.jpg"
            in 2..4 -> "snail-move-left-${i-1}.jpg"
            else -> "snail-move-right${(4-i)}.jpg"
        })
    }


    fun getCurrentSprite(): File {
        return when (directionFacing) {
            Direction.LEFT -> sprites[0]
            Direction.RIGHT -> sprites[1]
        }
    }

}


object SnailSprites {
    val sprites = List(8) { i ->
        java.io.File(when (i) {
            0 -> "snail-left.jpg"
            1 -> "snail-right.jpg"
            in 2..4 -> "snail-move-left-${i-1}.jpg"
            else -> "snail-move-right${(4-i)}.jpg"
        })
    }
}

class TansanianSnail2() {
    val directionFacing = Direction.LEFT
    val sprites = SnailSprites.sprites
}