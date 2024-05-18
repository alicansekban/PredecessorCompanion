package com.alican.predecessorcompanion.custom.more

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MoreClickableItem(item: MoreClickableComponentUIModel, onItemClicked: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clickable {
                onItemClicked.invoke()
            },
        elevation = CardDefaults.cardElevation(10.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(8.dp)
            ) {
                item.icon?.let {
                    Icon(
                        imageVector = it,
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.size(20.dp)
                    )
                }
                Text(
                    text = item.title,
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.secondary,
                        fontSize = 12.sp
                    ),

                )
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(8.dp)
            ) {
//                if (item.isNew) {
//                    Card(
//                        modifier = Modifier
//                            .wrapContentWidth()
//                            .padding(horizontal = 8.dp),
//                        elevation = CardDefaults.cardElevation(0.dp),
//                        shape = RoundedCornerShape(5.dp),
//                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
//                    ) {
//                        Box(contentAlignment = Alignment.Center) {
//                            Text(
//                                text = "Yeni",
//                                fontSize = 12.sp,
//                                lineHeight = 14.sp,
//                                maxLines = 1,
//                                modifier = Modifier
//                                    .wrapContentWidth()
//                                    .padding(4.dp)
//                            )
//                        }
//                    }
//                }
                Icon(
                    Icons.Outlined.KeyboardArrowRight,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier
                        .size(40.dp)
                        .padding(start = 4.dp, end = 6.dp)
                )
            }
        }

    }
}