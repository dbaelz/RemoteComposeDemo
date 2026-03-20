package de.dbaelz.rcdemo.navigation

enum class Screen(val title: String, val actions: Set<Action> = emptySet()) {
    HelloWorld(
        title = "HelloWorld",
        actions = setOf(Action.HelloWorldRefresh)
    )
}

