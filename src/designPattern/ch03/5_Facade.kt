package designPattern.ch03

import java.io.FileNotFoundException
import kotlin.io.path.Path

class ServerConfiguration {
    val port: Int = 8080
}

class Property

interface Parser {
    fun property(prop: String): Property
    fun server(propertyStrings: List<String>): ServerConfiguration
}

class JsonParser : Parser {
    override fun property(prop: String): Property {
        TODO("Not yet implemented")
    }

    override fun server(propertyStrings: List<String>): ServerConfiguration {
        TODO("Not yet implemented")
    }
}

class YamlParser : Parser {
    override fun property(prop: String): Property {
        TODO("Not yet implemented")
    }

    override fun server(propertyStrings: List<String>): ServerConfiguration {
        TODO("Not yet implemented")
    }
}

class Server private constructor(port: Int) {
    companion object {
        fun withPort(port: Int): Server {
            return Server(port)
        }
    }
}

fun Server.startFromConfiguration(fileLocation: String) {
    val path = Path(fileLocation)
    val lines = path.toFile().readLines()

    val configuration = try {
        JsonParser().server(lines)
    } catch (e: RuntimeException) {
        YamlParser().server(lines)
    }

    Server.withPort(configuration.port)
}


fun main() {
    try {
        val server = Server.withPort(0).startFromConfiguration("/path/to/config")
    } catch (e: FileNotFoundException) {
        println("If there was a file and a parser, it would have worked ")
    }
}
