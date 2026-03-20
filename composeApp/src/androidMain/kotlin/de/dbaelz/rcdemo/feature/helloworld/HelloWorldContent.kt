package de.dbaelz.rcdemo.feature.helloworld

import android.annotation.SuppressLint
import androidx.compose.remote.player.view.RemoteComposePlayer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.viewinterop.AndroidView
import de.dbaelz.rcdemo.repository.HelloWorldData

@SuppressLint("RestrictedApi")
@Composable
actual fun HelloWorldContent(data: HelloWorldData?) {
    if (data != null) {
        key(data.byteArray.contentHashCode()) {
            AndroidView(
                factory = { ctx ->
                    RemoteComposePlayer(ctx).apply {
                        setDocument(data.byteArray)
                    }
                }
            )
        }
    }
}