package com.example.whatsappstatus_saver.presentation.ui.navigation

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.VideoLibrary
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.VideoLibrary
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.whatsappstatus_saver.presentation.ui.screens.PicDetail
import com.example.whatsappstatus_saver.presentation.ui.screens.Saved
import com.example.whatsappstatus_saver.presentation.ui.screens.SettingScreen
import com.example.whatsappstatus_saver.presentation.ui.screens.VideoDetail
import com.example.whatsappstatus_saver.presentation.ui.screens.Videos
import com.example.whatsappstatus_saver.presentation.ui.screens.WhatsAppStatusScreen

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screens.ImagesScreen.route) {
        composable(Screens.ImagesScreen.route) {
            WhatsAppStatusScreen(navController)
        }
        composable(Screens.VideosScreen.route) {
            Videos(navController = navController)
        }
        composable(Screens.Saved.route) {
            Saved(navController = navController)
        }
        composable(Screens.SettingScreen.route) {
            SettingScreen(navController = navController)
        }
        composable(Screens.VideoDetail.route + "/{videoPath}") { backStackEntry ->
            val videoPath = backStackEntry.arguments?.getString("videoPath")
            if (videoPath != null) {
                VideoDetail(navController = navController, videoPath)
            }
        }

        composable(Screens.PicDetail.route + "/{PicPath}") { backStackEntry ->
            val PicPath = backStackEntry.arguments?.getString("PicPath")
            if (PicPath != null) {
                PicDetail(navController = navController, PicPath)
            }
        }
    }
}



sealed class Screens(
    val route: String,
    val title: String,
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector
) {

    object ImagesScreen : Screens(
        "Images", "Images", selectedIcon = Icons.Filled.Image, unSelectedIcon = Icons.Outlined.Image
    )

    object VideosScreen : Screens(
        "Videos",
        "Videos",
        selectedIcon = Icons.Filled.VideoLibrary,
        unSelectedIcon = Icons.Outlined.VideoLibrary
    )

    object Saved : Screens(
        "Saved",
        "Saved",
        selectedIcon = Icons.Filled.Bookmark,
        unSelectedIcon = Icons.Outlined.BookmarkBorder
    )


    object SettingScreen : Screens(
        "Setting",
        "Setting",
        selectedIcon = Icons.Filled.Settings,
        unSelectedIcon = Icons.Outlined.Settings
    )

    object VideoDetail : Screens(
        "VideoDetail",
        "VideoDetail",
        selectedIcon = Icons.Filled.Settings,
        unSelectedIcon = Icons.Outlined.Settings
    )

    object PicDetail : Screens(
        "PicDetail",
        "PicDetail",
        selectedIcon = Icons.Filled.Settings,
        unSelectedIcon = Icons.Outlined.Settings
    )
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NavEntry() {
    val navController = rememberNavController()
    var showBottomNav by remember { mutableStateOf(true) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    showBottomNav = when {
        currentRoute == null -> true
        currentRoute.startsWith(Screens.VideoDetail.route) -> false
        currentRoute.startsWith(Screens.PicDetail.route) -> false
        else -> true
    }

    Scaffold(
        bottomBar = {
            if (showBottomNav) {
                BottomNavigation(navController = navController)
            }
        }
    ) { innerPadding ->
        Navigation(navController = navController)
    }
}

@Composable
fun BottomNavigation(navController: NavController) {
    val item = listOf(
        Screens.ImagesScreen,
        Screens.VideosScreen,
        Screens.Saved,
        Screens.SettingScreen
    )

    NavigationBar(containerColor = Color(0XFF008069)) {
        val navStck by navController.currentBackStackEntryAsState()
        val current = navStck?.destination?.route
        item.forEach {
            NavigationBarItem(selected = current == it.route, onClick = {
                navController.navigate(it.route) {
                    navController.graph.let {
                        it.route?.let { it1 -> popUpTo(it1) }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            }, icon = {
                if (current == it.route) {
                    Icon(imageVector = it.selectedIcon, contentDescription = "", tint = Color.White)
                } else {
                    Icon(
                        imageVector = it.unSelectedIcon,
                        contentDescription = "",
                        tint = Color(0XFF70a59b)
                    )
                }
            }, label = {
                if (current == it.route) {
                    Text(text = it.title, color = Color.White, fontWeight = FontWeight.Medium)
                } else {
                    Text(
                        text = it.route, color = Color(0XFF70a59b), fontWeight = FontWeight.Medium
                    )
                }
            }, colors = NavigationBarItemDefaults.colors(indicatorColor = Color.Transparent))
        }
    }
}
