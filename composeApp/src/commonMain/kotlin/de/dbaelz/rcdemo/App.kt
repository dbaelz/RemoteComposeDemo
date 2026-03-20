package de.dbaelz.rcdemo

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import de.dbaelz.rcdemo.feature.helloworld.HelloWorldScreen
import de.dbaelz.rcdemo.navigation.Screen
import org.koin.compose.KoinApplication
import org.koin.compose.getKoin
import org.koin.dsl.koinConfiguration

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun App() {
    val navController: NavHostController = rememberNavController()


    KoinApplication(configuration = koinConfiguration(declaration = {
        modules(
            appModule(
                navController
            )
        )
    }), content = {
        MaterialTheme {
            val actionDispatcher: ActionDispatcher = getKoin().get()

            val backStackEntry by navController.currentBackStackEntryAsState()
            val currentScreen = Screen.valueOf(
                backStackEntry?.destination?.route ?: Screen.HelloWorld.name
            )

            Scaffold(
                modifier = Modifier.fillMaxSize(),
                topBar = {
                    TopAppBar(
                        title = { Text(currentScreen.title) },
                        navigationIcon = {
                            if (currentScreen != Screen.HelloWorld) {
                                IconButton(onClick = {
                                    navController.navigateUp()
                                }) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                                        contentDescription = "Back",
                                        tint = MaterialTheme.colorScheme.onPrimary
                                    )
                                }
                            }
                        },
                        actions = {
                            currentScreen.actions.forEach { action ->
                                IconButton(onClick = {
                                    actionDispatcher.dispatch(action)
                                }) {
                                    Icon(
                                        imageVector = action.icon,
                                        contentDescription = action.description,
                                        tint = MaterialTheme.colorScheme.onPrimary
                                    )
                                }
                            }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            titleContentColor = MaterialTheme.colorScheme.onPrimary
                        )
                    )
                }
            ) { paddingValues ->
                NavHost(
                    navController = navController,
                    startDestination = Screen.HelloWorld.name,
                    modifier = Modifier.fillMaxSize().padding(paddingValues)
                ) {
                    composable(route = Screen.HelloWorld.name) {
                        HelloWorldScreen()
                    }
                }
            }
        }

    })
}