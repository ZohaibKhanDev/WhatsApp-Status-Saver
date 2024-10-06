package com.example.whatsappstatus_saver.presentation.ui.navigation

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.VideoLibrary
import androidx.compose.material.icons.outlined.BookmarkBorder
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
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
fun Navigation(navController: NavHostController, selectedLanguage: String, ) {
    NavHost(navController = navController, startDestination = Screens.ImagesScreen.route) {
        composable(Screens.ImagesScreen.route) {
            WhatsAppStatusScreen(navController, selectedLanguage)
        }
        composable(Screens.VideosScreen.route) {
            Videos(navController = navController, selectedLanguage)
        }
        composable(Screens.Saved.route) {
            Saved(navController = navController, selectedLanguage)
        }
        composable(Screens.SettingScreen.route) {
            SettingScreen(navController = navController, selectedLanguage)
        }
        composable(Screens.VideoDetail.route + "/{videoPath}") { backStackEntry ->
            val videoPath = backStackEntry.arguments?.getString("videoPath")
            if (videoPath != null) {
                VideoDetail(navController = navController, videoPath, selectedLanguage)
            }
        }

        composable(Screens.PicDetail.route + "/{PicPath}") { backStackEntry ->
            val PicPath = backStackEntry.arguments?.getString("PicPath")
            if (PicPath != null) {
                PicDetail(navController = navController, PicPath, selectedLanguage)
            }
        }
    }
}


sealed class Screens(
    val route: String,
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector
) {
    object ImagesScreen : Screens(
        "Images", Icons.Filled.Image, Icons.Outlined.Image
    )

    object VideosScreen : Screens(
        "Videos", Icons.Filled.VideoLibrary, Icons.Outlined.VideoLibrary
    )

    object Saved : Screens(
        "Saved", Icons.Filled.Bookmark, Icons.Outlined.BookmarkBorder
    )

    object SettingScreen : Screens(
        "Setting", Icons.Filled.Settings, Icons.Outlined.Settings
    )

    object VideoDetail : Screens(
        "VideoDetail", Icons.Filled.Settings, Icons.Outlined.Settings
    )

    object PicDetail : Screens(
        "PicDetail", Icons.Filled.Settings, Icons.Outlined.Settings
    )

    fun getTitle(language: String): String {
        return when (this) {
            is ImagesScreen -> when (language) {
                "Urdu" -> "تصاویر"
                "Arabic" -> "صور"
                else -> "Images"
            }
            is VideosScreen -> when (language) {
                "Urdu" -> "ویڈیوز"
                "Arabic" -> "فيديوهات"
                else -> "Videos"
            }
            is Saved -> when (language) {
                "Urdu" -> "محفوظ شدہ"
                "Arabic" -> "المحفوظات"
                else -> "Saved"
            }
            is SettingScreen -> when (language) {
                "Urdu" -> "ترتیبات"
                "Arabic" -> "الإعدادات"
                else -> "Settings"
            }
            is VideoDetail -> when (language) {
                "Urdu" -> "ویڈیو کی تفصیل"
                "Arabic" -> "تفاصيل الفيديو"
                else -> "Video Detail"
            }
            is PicDetail -> when (language) {
                "Urdu" -> "تصویر کی تفصیل"
                "Arabic" -> "تفاصيل الصورة"
                else -> "Picture Detail"
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NavEntry() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("Settings", Context.MODE_PRIVATE)

    var selectedLanguage by remember {
        mutableStateOf(sharedPreferences.getString("selectedLanguage", "English") ?: "English")
    }

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
                BottomNavigation(navController = navController, selectedLanguage)
            }
        }
    ) {
        Navigation(navController = navController, selectedLanguage)
    }
}


@Composable
fun BottomNavigation(navController: NavController, selectedLanguage: String) {
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
            NavigationBarItem(
                selected = current == it.route,
                onClick = {
                    navController.navigate(it.route) {
                        navController.graph.let {
                            it.route?.let { it1 -> popUpTo(it1) }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                icon = {
                    if (current == it.route) {
                        Icon(imageVector = it.selectedIcon, contentDescription = "", tint = Color.White)
                    } else {
                        Icon(
                            imageVector = it.unSelectedIcon,
                            contentDescription = "",
                            tint = Color(0XFF70a59b)
                        )
                    }
                },
                label = {
                    Text(
                        text = it.getTitle(selectedLanguage),
                        color = if (current == it.route) Color.White else Color(0XFF70a59b),
                        fontWeight = FontWeight.Medium
                    )
                },
                colors = NavigationBarItemDefaults.colors(indicatorColor = Color.Transparent)
            )
        }
    }
}


