package de.dbaelz.rcdemo.feature.helloworld

import de.dbaelz.rcdemo.repository.HelloWorldData


object HelloWorldViewModelContract {
    data class State(
        val data: HelloWorldData? = null,
        val isLoading: Boolean = false,
        val message: String? = null,
    )

    sealed interface Event {
        data class HelloWorldSuccess(val data: HelloWorldData) : Event
        data class HelloWorldFailure(val error: Throwable) : Event
    }
}
