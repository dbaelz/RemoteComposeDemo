package de.dbaelz.rcdemo.feature.helloworld

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import de.dbaelz.rcdemo.repository.HelloWorldData

@Composable
actual fun HelloWorldContent(data: HelloWorldData?) {
    Text(
        text = "Remote Compose not supported yet!",
        style = MaterialTheme.typography.headlineMedium,
        color = MaterialTheme.colorScheme.onPrimaryContainer,
    )
}