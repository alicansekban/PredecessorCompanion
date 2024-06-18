package com.alican.predecessorcompanion.custom.navGraphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import com.alican.predecessorcompanion.domain.ui_model.players.PlayersUIModel
import com.alican.predecessorcompanion.ui.players.PlayersScreen
import com.alican.predecessorcompanion.ui.players.detail.PlayerDetailScreen
import com.alican.predecessorcompanion.utils.ScreenRoutes


fun NavGraphBuilder.leaderBoardNavGraph(
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
        startDestination = ScreenRoutes.LEADER_BOARD_ROUTE,
        route = ScreenRoutes.LEADER_BOARD_HOST_ROUTE
    ) {
        composable(
            route = ScreenRoutes.LEADER_BOARD_ROUTE
        ) {
            PlayersScreen(openPlayerDetail = { player ->

                navController.navigate(player)
            })
        }
        composable<PlayersUIModel> {
            val player = it.toRoute<PlayersUIModel>()
            PlayerDetailScreen(
                onBackClick = { navController.navigateUp() },
                player = player
            )
        }
    }
}