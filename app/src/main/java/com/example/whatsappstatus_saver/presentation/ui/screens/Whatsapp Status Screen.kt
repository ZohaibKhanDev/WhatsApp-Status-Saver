package com.example.whatsappstatus_saver.presentation.ui.screens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.whatsappstatus_saver.R
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class, ExperimentalMaterial3Api::class)
@Composable
fun WhatsAppStatusScreen(navController: NavController, selectedLanguage: String) {
    val permissionState =
        rememberPermissionState(permission = android.Manifest.permission.READ_EXTERNAL_STORAGE)

    LaunchedEffect(Unit) {
        permissionState.launchPermissionRequest()
    }

    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("Settings", Context.MODE_PRIVATE)
    var selectedLanguage by remember {
        mutableStateOf(sharedPreferences.getString("selectedLanguage", "English") ?: "English")
    }

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(
                    text = when (selectedLanguage) {
                        "Urdu" -> "اسٹیٹس امیجز"
                        "Arabic" -> "صور الحالة"
                        "English" -> "Status Images"
                        else -> "Status Images"
                    },
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }, actions = {
                Image(
                    painter = painterResource(id = R.drawable.premium),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .size(26.dp)
                )
            }, colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0XFF008069)))
        },
    ) {
        if (permissionState.status.isGranted) {
            DisplayWhatsAppStatuses(navController)
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = it.calculateTopPadding()),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "Please grant storage access to view WhatsApp statuses.",
                    fontSize = 21.sp,
                    fontWeight = FontWeight.Medium,
                )
            }
        }
    }
}