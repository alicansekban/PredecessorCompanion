package com.alican.predecessorcompanion.custom.bottomBar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Leaderboard
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.Museum
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material.icons.outlined.Leaderboard
import androidx.compose.material.icons.outlined.MoreHoriz
import androidx.compose.material.icons.outlined.Museum
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.alican.predecessorcompanion.utils.ScreenRoutes
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun BottomBar(
    navController: NavController,
    isBottomBarVisible : Boolean
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination


    val navigation: (String) -> Unit = { route ->
        if (route == ScreenRoutes.BACK_PRESSED) {
            navController.popBackStack()
        } else {
            navController.navigate(route) {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                // Avoid multiple copies of the same destination when
                // reselecting the same item
                launchSingleTop = true
                // Restore state when reselecting a previously selected item
                restoreState = true
            }
        }
    }
    val context = LocalContext.current
    val items = listOf(
        BottomNavigationItem(
            title = "Home",
            selectedIcon = Icons.Filled.Museum,
            unSelectedIcon = Icons.Outlined.Museum,
            route = ScreenRoutes.HOME_ROUTE
        ),
        BottomNavigationItem(
            title = "Search",
            selectedIcon = Icons.Filled.Leaderboard,
            unSelectedIcon = Icons.Outlined.Leaderboard,
            route = ScreenRoutes.LEADER_BOARD_HOST_ROUTE
        ),
        BottomNavigationItem(
            title = "Saved",
            selectedIcon = Icons.Filled.Bookmark,
            unSelectedIcon = Icons.Outlined.Bookmark,
            route = ScreenRoutes.SAVED_HOST_ROUTE
        ),
        BottomNavigationItem(
            title = "Builds",
            selectedIcon = Icons.Filled.Build,
            unSelectedIcon = Icons.Outlined.Build,
            route = ScreenRoutes.BUILD_HOST_ROUTE
        ),
        BottomNavigationItem(
            title = "More",
            selectedIcon = Icons.Filled.MoreHoriz,
            unSelectedIcon = Icons.Outlined.MoreHoriz,
            route = ScreenRoutes.MORE_HOST_ROUTE
        )
    )
    val selectedItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        //  AdmobBanner()
        AnimatedVisibility(visible = isBottomBarVisible) {
            NavigationBar {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                        onClick = {
                            navigation(item.route)
                        },
                        label = {
                            Text(
                                text = item.title,
                                fontSize = 10.sp,
                            )
                        },
                        icon = {
                            Icon(
                                modifier = Modifier.size(24.dp),
                                imageVector = if (index == selectedItemIndex) item.selectedIcon else item.unSelectedIcon,
                                contentDescription = item.title,
                            )
                        },
                        interactionSource = NoRippleInteractionSource,
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                            selectedTextColor = MaterialTheme.colorScheme.primary,
                            unselectedIconColor = MaterialTheme.colorScheme.onBackground,
                            unselectedTextColor = MaterialTheme.colorScheme.onBackground,
                            indicatorColor = MaterialTheme.colorScheme.background
                        )
                    )
                }
            }
        }
    }
}

private object NoRippleInteractionSource : MutableInteractionSource {

    override val interactions: Flow<Interaction> = emptyFlow()

    override suspend fun emit(interaction: Interaction) {}

    override fun tryEmit(interaction: Interaction) = true
}