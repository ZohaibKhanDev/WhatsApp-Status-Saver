package com.example.whatsappstatus_saver.presentation.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun WhatsAppStatusScreen() {
    val permissionState = rememberPermissionState(permission = android.Manifest.permission.READ_EXTERNAL_STORAGE)

    LaunchedEffect(Unit) {
        permissionState.launchPermissionRequest()
    }

    if (permissionState.status.isGranted) {
        DisplayWhatsAppStatuses()
    } else {
        Text("Please grant storage access to view WhatsApp statuses.")
    }
}
