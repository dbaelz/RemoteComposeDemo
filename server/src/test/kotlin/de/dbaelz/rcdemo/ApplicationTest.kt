package de.dbaelz.rcdemo

import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.testing.*
import java.util.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ApplicationTest {
    @Test
    fun testHelloWorldRoute() = testApplication {
        application {
            module()
        }

        val response = client.get(ApiRoute.HELLO_WORLD.fullResourcePath)
        assertEquals(HttpStatusCode.OK, response.status)
        val body = response.body<ByteArray>()
        assertTrue(body.isNotEmpty())
    }
}