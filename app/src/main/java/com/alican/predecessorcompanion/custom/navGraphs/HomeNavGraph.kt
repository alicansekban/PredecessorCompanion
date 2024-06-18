package com.alican.predecessorcompanion.custom.navGraphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import com.alican.predecessorcompanion.ui.heroes.HeroDetailScreen
import com.alican.predecessorcompanion.ui.home.HomeScreen
import com.alican.predecessorcompanion.utils.HeroDetail
import com.alican.predecessorcompanion.utils.ScreenRoutes


fun NavGraphBuilder.homeNavGraph(
    navController: NavController,
) {
    val navigation: (route: String) -> Unit = { route ->
        if (route == ScreenRoutes.BACK_PRESSED) {
            navController.popBackStack()
        } else {
            navController.navigate(route)
        }
    }

    navigation(
        startDestination = ScreenRoutes.HOME_ROUTE,
        route = ScreenRoutes.HOME_HOST_ROUTE
    ) {
        composable(
            route = ScreenRoutes.HOME_ROUTE
        ) {
            HomeScreen(
                onHeroClick = {
                    navController.navigate(it)
                }
            )
        }

    }

    composable<HeroDetail> {
        val args = it.toRoute<HeroDetail>()
        HeroDetailScreen(heroDetail = args)

    }
}