package de.dbaelz.rcdemo.navigation

enum class Screen(val title: String, val actions: Set<Action> = emptySet()) {
    Notes(
        title = "Notes",
        actions = setOf(Action.NotesRefresh)
    )
}

