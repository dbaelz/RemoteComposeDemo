package de.dbaelz.rcdemo

import androidx.navigation.NavHostController
import de.dbaelz.rcdemo.navigation.Action
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow


interface ActionDispatcher {
    val events: SharedFlow<Action>

    fun dispatch(action: Action)
}

class DefaultActionDispatcher(
    private val navController: NavHostController
) : ActionDispatcher {
    private val _events = MutableSharedFlow<Action>(replay = 0, extraBufferCapacity = 1)
    override val events: SharedFlow<Action> = _events

    override fun dispatch(action: Action) {
        when (action) {
            is Action.HelloWorldRefresh -> _events.tryEmit(action)
        }
    }
}