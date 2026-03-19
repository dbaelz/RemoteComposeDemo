package de.dbaelz.rcdemo.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Action(
    val icon: ImageVector,
    val description: String
) {
    data object NotesRefresh : Action(Icons.Default.Refresh, "Refresh notes")
}