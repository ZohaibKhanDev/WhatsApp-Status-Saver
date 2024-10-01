package com.example.whatsappstatus_saver.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.whatsappstatus_saver.presentation.ui.screens.WhatsAppStatusScreen

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screens.ImagesScreen.route) {
        composable(Screens.ImagesScreen.route) {
            WhatsAppStatusScreen(navController)
        }
        composable(Screens.VideosScreen.route) {

        }
        composable(Screens.Saved.route) {

        }

        composable(Screens.Setting.route) {

        }
    }
}

sealed class Screens(val route: String) {
    object ImagesScreen : Screens("Images")
    object VideosScreen : Screens("VideosScreen")
    object Saved : Screens("Saved")
    object Setting : Screens("Setting")
}


@Composable
fun BottomNavigation(navController: NavController) {
    val item = listOf(
        Screens.ImagesScreen,
        Screens.VideosScreen,
        Screens.Saved,
        Screens.Setting
    )
}