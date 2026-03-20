package de.dbaelz.rcdemo.feature.helloworld

import androidx.lifecycle.viewModelScope
import de.dbaelz.rcdemo.ActionDispatcher
import de.dbaelz.rcdemo.feature.BaseViewModel
import de.dbaelz.rcdemo.feature.helloworld.HelloWorldViewModelContract.Event
import de.dbaelz.rcdemo.feature.helloworld.HelloWorldViewModelContract.State
import de.dbaelz.rcdemo.navigation.Action
import de.dbaelz.rcdemo.repository.HelloWorldRepository
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class HelloWorldViewModel(
    private val helloWorldRepository: HelloWorldRepository,
    private val actionDispatcher: ActionDispatcher
) : BaseViewModel<State, Event>(
    initialState = State(isLoading = true)
) {
    init {
        viewModelScope.launch {
            event.map { reduce(state.value, it) }.collect { updateState(it) }
        }

        viewModelScope.launch {
            actionDispatcher.events.collect { action ->
                if (action is Action.HelloWorldRefresh) {
                    refresh()
                }
            }
        }

        refresh()
    }

    private fun reduce(state: State, event: Event): State {
        return when (event) {
            is Event.HelloWorldSuccess -> {
                state.copy(
                    data = event.data,
                    isLoading = false,
                )
            }

            is Event.HelloWorldFailure -> {
                state.copy(
                    data = null,
                    isLoading = false,
                    message = "Something went wrong"
                )
            }
        }
    }

    private fun refresh() {
        viewModelScope.launch {
            helloWorldRepository.getHelloWorldData().onSuccess {
                sendEvent(Event.HelloWorldSuccess(it))
            }.onFailure {
                sendEvent(Event.HelloWorldFailure(it))
            }
        }
    }
}