package de.dbaelz.rcdemo

import androidx.navigation.NavHostController
import de.dbaelz.rcdemo.feature.helloworld.HelloWorldViewModel
import de.dbaelz.rcdemo.repository.HelloWorldRepository
import de.dbaelz.rcdemo.repository.createHttpClient
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

fun appModule(navHostController: NavHostController) = module {
    single { createHttpClient() }
    single { HelloWorldRepository(get()) }

    single<ActionDispatcher> { DefaultActionDispatcher(navHostController) }

    viewModelOf(::HelloWorldViewModel)
}