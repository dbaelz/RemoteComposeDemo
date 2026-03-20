package de.dbaelz.rcdemo

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun main() {
    embeddedServer(Netty, port = SERVER_PORT, host = getServerHost(), module = Application::module)
        .start(wait = true)
}

fun Application.module(
    helloWorldRemote: HelloWorldRemote = HelloWorldRemote()
) {
    routing {
        get(ApiRoute.HELLO_WORLD.fullResourcePath) {
            val bytes = helloWorldRemote()
            call.respondBytes(bytes, contentType = ContentType.Application.OctetStream)
        }
    }
}