package dorkbox.kloudflare

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.time.Instant
import java.util.*

/**
 *
 */
object KloudflareTest {
    private val logger: Logger

    init {
        // fix logging context
        val property = System.getProperty("logging.context")
        if (property == null) {
            // we also have "webserver", but that is specified during webserver production launch
            System.setProperty("logging.context", "kloudflareAPI")
        }

        logger = LoggerFactory.getLogger(Kloudflare::class.java)
        Thread.setDefaultUncaughtExceptionHandler { t, e -> logger.error("Uncaught exception in thread: '{}'", t.name, e) }
    }

    @JvmStatic
    fun main(arguments: Array<String>) {
        // drop everything to lower case.
        for ((index, value) in arguments.withIndex()) {
            val lowerCase = value.toLowerCase(Locale.US)
            arguments[index] = lowerCase
        }

        logger.info("Starting : {} ", Instant.now().toString())

        // start up the application!
        val jvmName = System.getProperty("java.vm.name")
        val jvmVersion = System.getProperty("java.version")
        val jvmVendor = System.getProperty("java.vm.specification.vendor")
        logger.info("Execution JVM: $jvmVendor  $jvmName $jvmVersion")
        logger.info("Execution arguments: ${arguments.joinToString()}")


        val email = "user@example.com"
        val token = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"

        val kloudflare = Kloudflare(email, token)

        println(kloudflare.getUserDetails())
        println(kloudflare.getUserBillingProfile())
        println(kloudflare.getUserBillingHistory())


        val zones = kloudflare.listZones()

//        println(kloudflare.getZoneRatePlans("123"))
//        println(kloudflare.getZoneRatePlans("123"))
//        println(kloudflare.getZoneSettings("123"))
//        println(kloudflare.listDnsRecords("123"))
//        println(kloudflare.listAccessRules())



//        val history = cloudflare.getUserBillingHistory(email, token).execute()
//        println("user: ${history.body()?.result}")
//
//        val zones = cloudflare.listZones(email, token).execute().body()?.result
//        println("user: ${zones.body()?.result}")

//        val zoneRatePlans = cloudflare.getZoneRatePlans(email, token, "123").execute()
//        println("user: ${zoneRatePlans.body()?.result}")
//
//        val zoneSettings = cloudflare.getZoneSettings(email, token, "123").execute()
//        println("user: ${zoneSettings.body()?.result}")
//
//        val dnsRecords = cloudflare.listDnsRecords(email, token, "123").execute()
//        println("user: ${dnsRecords.body()?.result}")


        kloudflare.shutdown()
    }
}
