package designPattern.ch03

interface CommissionRepository {
    operator fun get(commissionName: String): String
    operator fun set(commissionName: String, artistName: String)
}

class DefaultCommissionRepository: CommissionRepository {
    private val commissionArtists = mutableMapOf("SD Commission" to "Jinvicky")

    override fun get(commissionName: String) : String {
        return commissionArtists[commissionName] ?: "Unknown"
    }

    override fun set(commissionName: String, artistName: String) {
        commissionArtists[commissionName] = artistName;
    }
}

class LoggingGetArtist(private val repository: CommissionRepository) : CommissionRepository by repository  {
    override fun get(commissionName: String): String {
        return repository[commissionName]
    }
}

fun main() {
    val commissionRepository = DefaultCommissionRepository()
    val withLogging          = LoggingGetArtist(commissionRepository)

    try {
        withLogging["SD Commission"] = "Jinvicky"
    } catch (e: IllegalStateException) {
        println(e)
    }

    println(withLogging is LoggingGetArtist)
}