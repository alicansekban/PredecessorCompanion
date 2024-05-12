package com.alican.predecessorcompanion.custom.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.alican.predecessorcompanion.custom.navGraphs.buildNavGraph
import com.alican.predecessorcompanion.custom.navGraphs.homeNavGraph
import com.alican.predecessorcompanion.custom.navGraphs.leaderBoardNavGraph
import com.alican.predecessorcompanion.custom.navGraphs.moreNavGraph
import com.alican.predecessorcompanion.custom.navGraphs.savedNavGraph
import com.alican.predecessorcompanion.utils.ScreenRoutes

@Composable
fun MainNavigation(
    navController: NavHostController,
    modifier: Modifier,
    isDarkMode: Boolean,
    themeChanged: (Boolean) -> Unit,
    drawerState: DrawerState,
    shouldBottomBarVisible: (Boolean) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = ScreenRoutes.HOME_HOST_ROUTE,
        modifier = modifier,
        enterTransition = {
            slideIntoContainer(
                animationSpec = tween(200, easing = EaseIn),
                towards = AnimatedContentTransitionScope.SlideDirection.Left
            )
        },
        exitTransition = {
            slideOutOfContainer(
                animationSpec = tween(200, easing = EaseOut),
                towards = AnimatedContentTransitionScope.SlideDirection.Left
            )
        }

    ) {
        homeNavGraph(
            navController = navController,
            isDarkMode = isDarkMode,
            shouldBottomBarVisible = shouldBottomBarVisible
        )
        leaderBoardNavGraph(
            navController = navController,
            isDarkMode = isDarkMode,
            shouldBottomBarVisible = shouldBottomBarVisible
        )
        savedNavGraph(
            navController = navController,
            isDarkMode = isDarkMode,
            shouldBottomBarVisible = shouldBottomBarVisible
        )
        buildNavGraph(
            navController = navController,
            isDarkMode = isDarkMode,
            shouldBottomBarVisible = shouldBottomBarVisible
        )
        moreNavGraph(
            navController = navController,
            isDarkMode = isDarkMode,
            shouldBottomBarVisible = shouldBottomBarVisible
        )

    }

}

