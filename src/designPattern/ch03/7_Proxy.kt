package designPattern.ch03

import java.net.URL

data class CatImage(
    val thumbnailUrl: String,
    val url: String
) {
    val image: ByteArray by lazy {
        println("Fetching image, please wait")
        URL(url).readBytes()
    }
}

fun main() {
    val cat = CatImage(
        "https://i.chzbgr.com/full/9026714368/hBB09ABBE/i-will-has-attention",
        "https://i.chzbgr.com/full/9026714368/hBB09ABBE/i-will-has-attention"
    )

    println(cat.image.size)
    println(cat.image.size)
}