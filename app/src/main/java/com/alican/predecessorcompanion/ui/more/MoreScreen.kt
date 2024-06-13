package com.alican.predecessorcompanion.ui.more

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alican.predecessorcompanion.R
import com.alican.predecessorcompanion.custom.more.MoreClickableComponentUIModel
import com.alican.predecessorcompanion.custom.more.MoreClickableItem
import com.alican.predecessorcompanion.utils.ScreenRoutes

@Composable
fun MoreScreen(
    modifier: Modifier = Modifier,
    navigate: (String) -> Unit
) {
    val context = LocalContext.current

    val items = arrayListOf(
        MoreClickableComponentUIModel(
            title = "Items",
            icon = Icons.Filled.AccountBox,
            route = ScreenRoutes.MORE_ITEMS_ROUTE
        ),

        )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.horizontalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.primaryContainer,
                        MaterialTheme.colorScheme.primary
                    )
                )
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "",
            modifier = Modifier
                .size(80.dp)
                .padding(top = 16.dp)
                .clip(
                    CircleShape
                ),
            tint = Color.White
        )
        Text(
            text = "Predecessor Companion",
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurface,
            style = TextStyle(
                fontSize = 14.sp
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp)
                .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                .background(MaterialTheme.colorScheme.primaryContainer),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            items.forEach { item ->
                MoreClickableItem(item = item) {
                    navigate(item.route.toString())
                }
            }
        }
    }
}