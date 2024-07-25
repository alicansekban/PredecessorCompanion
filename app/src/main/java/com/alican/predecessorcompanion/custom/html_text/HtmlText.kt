package com.alican.predecessorcompanion.custom.html_text

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import com.google.android.material.textview.MaterialTextView

@Composable
fun HtmlText(
    modifier: Modifier = Modifier,
    text: String
) {

    val spannedText = HtmlCompat.fromHtml(
        text, 0
    )
    Column(
        modifier = modifier
    ) {
        AndroidView(
            modifier = Modifier.padding(16.dp),
            factory = { MaterialTextView(it) },
            update = { it.text = spannedText }
        )
    }


}