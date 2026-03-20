package de.dbaelz.rcdemo.repository

import kotlin.time.Clock
import kotlin.time.Instant

data class HelloWorldData(val byteArray: ByteArray, val lastUpdate: Instant = Clock.System.now()) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as HelloWorldData

        return byteArray.contentEquals(other.byteArray)
    }

    override fun hashCode(): Int {
        return byteArray.contentHashCode()
    }
}