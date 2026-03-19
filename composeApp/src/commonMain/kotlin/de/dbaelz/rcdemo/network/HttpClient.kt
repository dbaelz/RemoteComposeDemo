package de.dbaelz.rcdemo.network

import de.dbaelz.rcdemo.BASIC_AUTH_PASSWORD
import de.dbaelz.rcdemo.BASIC_AUTH_USERNAME
import de.dbaelz.rcdemo.SERVER_HOST
import de.dbaelz.rcdemo.SERVER_PORT
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.http.*

fun createHttpClient(
    engine: HttpClientEngine? = null
): HttpClient {
    val baseUrl: String = "http://$SERVER_HOST:$SERVER_PORT"

    val clientConfig: HttpClientConfig<*>.() -> Unit = {
        install(Auth) {
            basic {
                credentials {
                    BasicAuthCredentials(
                        username = BASIC_AUTH_USERNAME,
                        password = BASIC_AUTH_PASSWORD
                    )
                }
            }
        }

        defaultRequest {
            url {
                host = SERVER_HOST
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