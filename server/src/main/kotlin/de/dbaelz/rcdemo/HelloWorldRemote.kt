package de.dbaelz.rcdemo

import androidx.compose.remote.core.operations.layout.managers.BoxLayout
import androidx.compose.remote.core.operations.layout.managers.CoreText
import androidx.compose.remote.creation.JvmRcPlatformServices
import androidx.compose.remote.creation.RemoteComposeWriter
import androidx.compose.remote.creation.modifiers.RecordingModifier
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

class HelloWorldRemote {
    private var counter = 1

    operator fun invoke(): ByteArray {
        // androidx.compose.remote:remote-creation-compose dependency is not available on JVM
        // Therefore, we have to create the Composable with RemoteComposeWriter
        val writer = RemoteComposeWriter(JvmRcPlatformServices())

        writer.root {
            writer.column(
                RecordingModifier()
                    .fillMaxSize()
                    .padding(32),
                BoxLayout.CENTER,
                BoxLayout.TOP
            ) {
                text(writer, "Hello World", 48.dp)

                text(writer, "Call counter: ${counter++}", 24.dp)
            }
        }

        return writer.encodeToByteArray()
    }

    private fun text(writer: RemoteComposeWriter, text: String, fontSize: Dp) {

        writer.textComponent(
            RecordingModifier(),
            writer.addText(text),
            0xFF757575L.toInt(),
            fontSize.value * density.density,
            0,
            400f,
            null,
            CoreText.TEXT_ALIGN_START,
            0,
            Int.MAX_VALUE
        ) {}
    }

    private companion object {
        // Fixed value, should be handed over by calling client?
        val density = Density(3f)
    }
}