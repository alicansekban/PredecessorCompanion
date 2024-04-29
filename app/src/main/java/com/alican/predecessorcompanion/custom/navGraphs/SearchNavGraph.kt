package com.alican.predecessorcompanion.custom.navGraphs

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.tween
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.alican.predecessorcompanion.utils.ScreenRoutes


fun NavGraphBuilder.searchNavGraph(
    navController: NavController,
    isDarkMode: Boolean,
    shouldBottomBarVisible: (Boolean) -> Unit
) {
    val navigation: (route: String) -> Unit = { route ->
        if (route == ScreenRoutes.BACK_PRESSED) {
            navController.popBackStack()
        } else {
            navController.navigate(route)
        }
    }

    navigation(
        startDestination = ScreenRoutes.SEARCH_ROUTE,
        route = ScreenRoutes.SEARCH_HOST_ROUTE
    ) {
        composable(
            route = ScreenRoutes.SEARCH_ROUTE
        ) {

        }

    }
}