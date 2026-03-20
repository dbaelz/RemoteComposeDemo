package de.dbaelz.rcdemo

import androidx.compose.remote.core.operations.layout.managers.CoreText
import androidx.compose.remote.creation.JvmRcPlatformServices
import androidx.compose.remote.creation.RemoteComposeWriter
import androidx.compose.remote.creation.modifiers.RecordingModifier

class HelloWorldRemote {
    private var counter = 1

    operator fun invoke(): ByteArray {
        // androidx.compose.remote:remote-creation-compose dependency is not available on JVM
        // Therefore, we have to create the Composable with RemoteComposeWriter
        val writer = RemoteComposeWriter(JvmRcPlatformServices())

        writer.root {
            writer.textComponent(
                RecordingModifier().fillMaxSize(),
                writer.addText("Hello World! \nCounter: ${counter++}"),
                0xFF757575L.toInt(),
                128f,
                0,
                400f,
                null,
                CoreText.TEXT_ALIGN_START,
                0,
                Int.MAX_VALUE
            ) {}
        }

        return writer.encodeToByteArray()
    }
}