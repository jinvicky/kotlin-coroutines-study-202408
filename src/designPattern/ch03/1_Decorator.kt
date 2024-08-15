package designPattern.ch03

fun main() {
    val starTrekRepository          = DefaultStarTrekRepository()
    val withValidating              = ValidatingAdd(starTrekRepository)
    val withLoggingAndValidating    = LoggingGetCaptain(withValidating)

    withLoggingAndValidating["USS Enterprise"]

    try {
        withLoggingAndValidating["USS Voyager"] = "Kathryn Janeway"
    } catch (e: IllegalStateException) {
        println(e)
    }

    println(withLoggingAndValidating is LoggingGetCaptain)
    println(withLoggingAndValidating is StarTrekRepository)
}

interface StarTrekRepository {
    operator fun get(starshipName: String): String
    operator fun set(starshipName: String, captainName: String)
}

class DefaultStarTrekRepository: StarTrekRepository {
    private val starshipCaptains = mutableMapOf("USS Enterprise" to "Jean-Luc Picard")

    override fun get(starshipName: String) : String {
        return starshipCaptains[starshipName] ?: "Unknown"
    }

    override fun set(starshipName: String, captainName: String) {
        starshipCaptains[starshipName] = captainName;
    }
}

class LoggingGetCaptain(private val repository: StarTrekRepository) : StarTrekRepository by repository {
    override fun get(starshipName: String): String {
        println("Getting captain for $starshipName")
        return repository[starshipName]
    }
}

class ValidatingAdd(private val repository: StarTrekRepository) : StarTrekRepository by repository {
    private val maxNameLength = 15
    override fun set(starshipName: String, captainName: String) {
        require(captainName.length < maxNameLength) {
            "$captainName name is longer than $maxNameLength characters!"
        }

        repository[starshipName] = captainName
    }
}