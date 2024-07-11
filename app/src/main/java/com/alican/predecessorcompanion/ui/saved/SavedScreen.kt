package com.alican.predecessorcompanion.ui.saved

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.alican.predecessorcompanion.custom.tab.getTabList
import com.alican.predecessorcompanion.ui.saved.builds.SavedBuilsScreen
import com.alican.predecessorcompanion.ui.saved.players.SavedPlayersScreen

@Composable
fun SavedScreen() {
    var tabIndex by remember {
        mutableStateOf(0)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        TabRow(
            selectedTabIndex = tabIndex,
            containerColor = MaterialTheme.colorScheme.onPrimary,
            divider = {},
            indicator = {
                HorizontalDivider(
                    modifier = Modifier
                        .tabIndicatorOffset(it[tabIndex])
                )
            }
        ) {
            getTabList().forEachIndexed { index, tabItemModel ->
                Tab(
                    selected = tabIndex == index,
                    onClick = {
                        tabIndex = index
                    },
                    text = {
                        Text(
                            text = tabItemModel.title,
                            style = TextStyle(
                                color = MaterialTheme.colorScheme.secondary,
                                fontSize = 12.sp
                            ),
                        )
                    }
                )
            }
        }
        when (tabIndex) {
            0 -> {
                SavedPlayersScreen()
            }

            1 -> {
                SavedBuilsScreen()
            }
        }
    }
}
