package de.dbaelz.rcdemo.feature.helloworld

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import de.dbaelz.rcdemo.feature.Loading
import de.dbaelz.rcdemo.repository.HelloWorldData
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HelloWorldScreen() {
    val viewModel: HelloWorldViewModel = koinViewModel()

    val state by viewModel.state.collectAsState()

    if (state.isLoading) {
        Loading()
    } else if (state.message != null) {
        Text(state.message ?: "")
    } else {
        HelloWorldContent(state.data)
    }
}

@Composable
expect fun HelloWorldContent(data: HelloWorldData?)