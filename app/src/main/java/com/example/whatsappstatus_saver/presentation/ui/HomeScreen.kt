package com.example.whatsappstatus_saver.presentation.ui

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import java.io.File


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatusScreen(statusItems: List<String>) {
    val context = LocalContext.current
    var hasStoragePermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        )
    }


    if (!hasStoragePermission) {
        PermissionRequest(permission = Manifest.permission.READ_EXTERNAL_STORAGE,
            onPermissionGranted = { hasStoragePermission = it }
        )
    } else {
        StatusList(statusItems)
    }
}

@Composable
fun StatusList(statusItems: List<String>) {
    if (statusItems.isEmpty()) {
        Text(
            text = "No WhatsApp Statuses Found",
            modifier = Modifier.fillMaxSize(),
            textAlign = TextAlign.Center
        )
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(statusItems) { statusPath ->
                StatusItem(statusPath)
            }
        }
    }
}

@Composable
fun StatusItem(statusPath: String) {
    val imageBitmap = remember(statusPath) {
        loadImageBitmap(statusPath)
    }
    Log.d("StatusItem", "Displaying status: $statusPath")
    if (imageBitmap != null) {
        Image(
            bitmap = imageBitmap,
            contentDescription = "WhatsApp Status",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentScale = ContentScale.Crop
        )
    } else {
        Text("Failed to load image: $statusPath")
    }
}

@Composable
fun PermissionRequest(permission: String, onPermissionGranted: (Boolean) -> Unit) {
    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        onPermissionGranted(isGranted)
    }

    SideEffect {
        launcher.launch(permission)
    }
}


fun loadImageBitmap(imagePath: String): ImageBitmap? {
    return try {
        val file = File(imagePath)
        if (file.exists()) {
            val bitmap = BitmapFactory.decodeFile(file.absolutePath)
            bitmap?.asImageBitmap()
        } else {
            Log.e("StatusItem", "File does not exist: $imagePath")
            null
        }
    } catch (e: Exception) {
        Log.e("StatusItem", "Error loading image: ${e.message}")
        null
    }
}