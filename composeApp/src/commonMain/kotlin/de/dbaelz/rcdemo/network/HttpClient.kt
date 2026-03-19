package de.dbaelz.rcdemo.network

import de.dbaelz.rcdemo.SERVER_HOST
import de.dbaelz.rcdemo.SERVER_PORT
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.client.HttpClient as KtorHttpClient

class HttpClient(
    private val baseUrl: String = "http://$SERVER_HOST:$SERVER_PORT"
) {
    private val client = KtorHttpClient {
        expectSuccess = false
        install(HttpTimeout) {
            connectTimeoutMillis = 10_000
            requestTimeoutMillis = 10_000
            socketTimeoutMillis = 10_000
        }
    }

    suspend fun getRemoteDocument(path: String = "/"): Result<ByteArray> {
        return try {
            val response = client.get("$baseUrl$path")

            if (!response.status.isSuccess()) {
                return Result.failure(Exception("HTTP ${response.status.value}: ${response.status.description}"))
            }

            val bytes: ByteArray = response.body()
            if (bytes.isEmpty()) {
                return Result.failure(Exception("Empty response body"))
            }

            Result.success(bytes)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}