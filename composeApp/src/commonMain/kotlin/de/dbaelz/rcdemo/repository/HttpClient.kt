package de.dbaelz.rcdemo.repository

import de.dbaelz.rcdemo.SERVER_PORT
import de.dbaelz.rcdemo.getServerHost
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.plugins.*
import io.ktor.http.*

fun createHttpClient(
    engine: HttpClientEngine? = null
): HttpClient {
    val clientConfig: HttpClientConfig<*>.() -> Unit = {
        defaultRequest {
            url {
                host = getServerHost()
                port = SERVER_PORT
                path("/")
            }
        }
    }

    return if (engine == null) {
        HttpClient(clientConfig)
    } else {
        HttpClient(engine, clientConfig)
    }
}