package de.dbaelz.rcdemo.feature.notes

import androidx.lifecycle.viewModelScope
import de.dbaelz.rcdemo.feature.BaseViewModel
import de.dbaelz.rcdemo.feature.notes.NotesViewModelContract.Event
import de.dbaelz.rcdemo.feature.notes.NotesViewModelContract.State
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class NotesViewModel() : BaseViewModel<State, Event>(
    initialState = State(isLoading = true)
) {
    init {
        viewModelScope.launch {
            event.map { reduce(state.value, it) }.collect { updateState(it) }
        }
    }

    private fun reduce(state: State, event: Event): State {
        return state
    }
}