package com.alican.predecessorcompanion.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.alican.predecessorcompanion.custom.bottomBar.BottomBar
import com.alican.predecessorcompanion.custom.navigation.MainNavigation
import com.alican.predecessorcompanion.utils.theme.PredecessorCompanionTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PredecessorCompanionTheme {
                val navController = rememberNavController()
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                // A surface container using the 'background' color from the theme
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        BottomBar(navController = navController, isBottomBarVisible = true)
                    }
                ) { paddingValues ->
                    MainNavigation(
                        navController = navController,
                        modifier = Modifier.padding(paddingValues),
                        isDarkMode = false,
                        themeChanged = {},
                        drawerState = drawerState,
                        shouldBottomBarVisible = {
                        }
                    )
                }
            }
        }
    }
}