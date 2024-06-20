package com.alican.predecessorcompanion.custom.navGraphs

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import com.alican.predecessorcompanion.domain.ui_model.items.ItemUIModel
import com.alican.predecessorcompanion.ui.more.MoreScreen
import com.alican.predecessorcompanion.ui.more.item_detail.ItemDetailScreen
import com.alican.predecessorcompanion.ui.more.item_detail.ItemDetailViewModel
import com.alican.predecessorcompanion.ui.more.items.ItemsScreen
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
            ItemsScreen(
                goToDetail = {
                    navController.navigate(it)
                }
            )
        }
        composable<ItemUIModel> {
            val args = it.toRoute<ItemUIModel>()
            val viewmodel = hiltViewModel<ItemDetailViewModel>()
            ItemDetailScreen(
                viewModel = viewmodel,
                model = args,
                onBackClick = { navController.navigateUp() })
        }
    }
}