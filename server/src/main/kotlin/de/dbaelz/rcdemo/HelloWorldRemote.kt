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
                writer.text(
                    writer = writer,
                    text = "Hello World!",
                    fontSize = 42.dp
                )

                writer.spacer(24.dp)

                writer.text(
                    writer = writer,
                    text = "This Composable is generated on the server and updates with every refresh.",
                    color = 0xFF000000L,
                    fontSize = 22.dp
                )

                writer.spacer(16.dp)

                writer.text(
                    writer = writer,
                    text = "Refresh counter: ${counter++}",
                    color = 0xFF3700B3L
                )
            }
        }

        return writer.encodeToByteArray()
    }

    private fun RemoteComposeWriter.text(
        writer: RemoteComposeWriter,
        text: String,
        color: Long = 0xFF6200EE,
        fontSize: Dp = 22.dp
    ) {

        writer.textComponent(
            RecordingModifier(),
            writer.addText(text),
            color.toInt(),
            fontSize.value * density.density,
            0,
            400f,
            null,
            CoreText.TEXT_ALIGN_START,
            0,
            Int.MAX_VALUE
        ) {}
    }

    private fun RemoteComposeWriter.spacer(height: Dp) {
        box(RecordingModifier().height(height.value * density.density))
    }

    private companion object {
        // Fixed value, should be handed over by calling client?
        val density = Density(3f)
    }
}