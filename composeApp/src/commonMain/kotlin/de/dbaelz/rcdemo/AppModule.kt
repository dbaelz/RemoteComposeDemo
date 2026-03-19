package de.dbaelz.rcdemo

import androidx.navigation.NavHostController
import de.dbaelz.rcdemo.feature.notes.NotesViewModel
import de.dbaelz.rcdemo.network.createHttpClient
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

fun appModule(navHostController: NavHostController) = module {
    single { createHttpClient() }

    single<ActionDispatcher> { DefaultActionDispatcher(navHostController) }

    viewModelOf(::NotesViewModel)
}