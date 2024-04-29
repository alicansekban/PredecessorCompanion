package com.alican.predecessorcompanion.custom.navGraphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.alican.predecessorcompanion.ui.heroes.HeroesScreen
import com.alican.predecessorcompanion.utils.ScreenRoutes


fun NavGraphBuilder.heroesNavGraph(
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
        startDestination = ScreenRoutes.HEROES_ROUTE,
        route = ScreenRoutes.HEROES_HOST_ROUTE
    ) {
        composable(
            route = ScreenRoutes.HEROES_ROUTE
        ) {
            HeroesScreen()
        }

    }
}