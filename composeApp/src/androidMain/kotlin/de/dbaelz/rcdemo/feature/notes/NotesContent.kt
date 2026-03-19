package de.dbaelz.rcdemo.feature.notes

import android.annotation.SuppressLint
import androidx.compose.remote.player.view.RemoteComposePlayer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.viewinterop.AndroidView

@Composable
actual fun NotesContent(data: ByteArray?) {
    if (data != null) {
        key(data.contentHashCode()) {
            AndroidView(
                factory = { ctx ->
                    RemoteComposePlayer(ctx).apply {
                        setDocument(data)
                    }
                }
            )
        }
    }
}