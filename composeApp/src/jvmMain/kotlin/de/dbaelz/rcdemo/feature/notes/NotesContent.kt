package de.dbaelz.rcdemo.feature.notes

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
actual fun NotesContent(data: ByteArray?) {
    Text(
        text = "Remote Compose not supported yet!",
        style = MaterialTheme.typography.headlineMedium,
        color = MaterialTheme.colorScheme.onPrimaryContainer,
    )
}