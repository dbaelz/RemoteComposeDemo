package de.dbaelz.rcdemo

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform