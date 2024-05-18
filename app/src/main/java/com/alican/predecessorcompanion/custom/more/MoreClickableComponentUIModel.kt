package com.alican.predecessorcompanion.custom.more

import androidx.compose.ui.graphics.vector.ImageVector

data class MoreClickableComponentUIModel(
    val title: String,
    val icon: ImageVector? = null,
    val isNew: Boolean = false,
    val route: String? = null,
    val query: String? = null,
)


