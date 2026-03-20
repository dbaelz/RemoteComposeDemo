package de.dbaelz.rcdemo.navigation

enum class Screen(val title: String, val actions: Set<Action> = emptySet()) {
    HelloWorld(
        title = "Hello World",
        actions = setOf(Action.HelloWorldRefresh)
    )
}

