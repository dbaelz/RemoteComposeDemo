package de.dbaelz.rcdemo.repository

import de.dbaelz.rcdemo.ApiRoute
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class HelloWorldRepository(
    val httpClient: HttpClient
) {
    suspend fun getHelloWorldData(): Result<HelloWorldData> {
        return try {
            val response = httpClient.get(ApiRoute.HELLO_WORLD.fullResourcePath).body<ByteArray>()
            Result.success(HelloWorldData(response))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}