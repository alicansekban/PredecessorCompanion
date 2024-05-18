package com.alican.predecessorcompanion.custom.navGraphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.alican.predecessorcompanion.ui.more.MoreScreen
import com.alican.predecessorcompanion.utils.ScreenRoutes


fun NavGraphBuilder.moreNavGraph(
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
        startDestination = ScreenRoutes.MORE_ROUTE,
        route = ScreenRoutes.MORE_HOST_ROUTE
    ) {
        composable(
            route = ScreenRoutes.MORE_ROUTE
        ) {
        MoreScreen(navigate = navigation)
        }
        composable(
            route = ScreenRoutes.MORE_ITEMS_ROUTE
        ) {
//            (
//                onBackPressed = {
//                    navigation(it)
//                }
//            )
        }

    }
}