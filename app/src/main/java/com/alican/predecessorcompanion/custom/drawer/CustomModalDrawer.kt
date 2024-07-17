package com.alican.predecessorcompanion.custom.drawer

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alican.museums.utils.widthPercent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun CustomModalDrawer(
    drawerState: DrawerState,
    scope: CoroutineScope,
    title: String,
    items: List<HeroStatisticsFilterModel>,
    onItemClicked: (HeroStatisticsFilterModel) -> Unit,
    content: @Composable () -> Unit,
) {
    val configuration = LocalConfiguration.current
    val listState = rememberLazyListState()

    var searchText by rememberSaveable {
        mutableStateOf("")
    }

    val filteredItems = filterOptions(searchText, items)


    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        ModalNavigationDrawer(
            drawerState = drawerState,
            gesturesEnabled = drawerState.isOpen,
            drawerContent = {
                CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                    Column(
                        Modifier
                            .widthPercent(0.60f, configuration)
                            .fillMaxHeight()
                            .background(MaterialTheme.colorScheme.primaryContainer)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 12.dp)
                                .background(MaterialTheme.colorScheme.primaryContainer)
                                .height(50.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Box(
                                modifier = Modifier.fillMaxSize()
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Close, "",
                                    modifier = Modifier
                                        .size(34.dp)
                                        .padding(start = 16.dp, top = 16.dp)
                                        .clickable {
                                            scope.launch {
                                                searchText = ""
                                                drawerState.close()
                                                listState.scrollToItem(0)
                                            }
                                        }
                                        .height(IntrinsicSize.Min),
                                    tint = MaterialTheme.colorScheme.onSecondary
                                )


                                Text(
                                    text = title,
                                    textAlign = TextAlign.Center,
                                    color = MaterialTheme.colorScheme.secondary,
                                    modifier = Modifier.align(Alignment.Center),
                                    style = TextStyle(
                                        fontSize = 14.sp
                                    ),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }

                        LazyColumn(
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.fillMaxSize(),
                            state = listState
                        ) {
                            itemsIndexed(
                                items = filteredItems
                            ) { index, item ->
                                CustomDrawerItem(
                                    onItemClicked = {
                                        scope.launch {
                                            searchText = ""
                                            drawerState.close()
                                            listState.scrollToItem(0)
                                        }
                                        onItemClicked(item)
                                    }, model = item
                                )
                            }
                        }
                    }
                }

            },
            content = {
                CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                    content()
                }
            }
        )
    }
}

fun filterOptions(
    text: String,
    items: List<HeroStatisticsFilterModel>
): List<HeroStatisticsFilterModel> {
    return if (text.isBlank()) {
        items
    } else {
        items.filter { item ->
            item.title.contains(text, ignoreCase = true)
        }
    }
}