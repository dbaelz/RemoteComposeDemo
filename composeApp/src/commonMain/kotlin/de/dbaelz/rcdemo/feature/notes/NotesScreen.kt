package de.dbaelz.rcdemo.feature.notes

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import de.dbaelz.rcdemo.feature.Loading
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun NotesScreen() {
    val viewModel: NotesViewModel = koinViewModel()

    val state by viewModel.state.collectAsState()

    if (state.isLoading) {
        Loading()
    } else if (state.message != null) {
        Text(state.message ?: "")
    } else {
        NotesContent(state.remoteDocument)
    }
}

@Composable
expect fun NotesContent(data: ByteArray?)