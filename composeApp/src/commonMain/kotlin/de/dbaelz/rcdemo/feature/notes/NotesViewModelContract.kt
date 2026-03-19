package de.dbaelz.rcdemo.feature.notes


object NotesViewModelContract {
    data class State(
        val remoteDocument: ByteArray? = null,
        val isLoading: Boolean = false,
        val message: String? = null,
    )

    sealed interface Event {

    }
}
