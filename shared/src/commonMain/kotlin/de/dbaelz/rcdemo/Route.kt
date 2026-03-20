package de.dbaelz.rcdemo

enum class ApiRoute(override val resource: String) : BaseRoute {
    HELLO_WORLD("helloworld"),
}

interface BaseRoute {
    val resource: String

    val resourcePath: String
        get() = "/$resource"

    val fullResourcePath: String
        get() = "$apiBasePath/$resource"
}

val apiResource: String
    get() = "api"

val apiBasePath: String
    get() = "/$apiResource"




