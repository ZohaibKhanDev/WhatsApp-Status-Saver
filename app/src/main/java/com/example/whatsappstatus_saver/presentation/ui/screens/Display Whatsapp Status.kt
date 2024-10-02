package com.example.whatsappstatus_saver.presentation.ui.screens

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.io.File

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisplayWhatsAppStatuses() {
    val statuses = remember { mutableStateListOf<File>() }

    LaunchedEffect(Unit) {
        val whatsappStatusFolder = File(
            Environment.getExternalStorageDirectory()
                .toString() + "/Android/media/com.whatsapp/WhatsApp/Media/.Statuses"
        )

        if (whatsappStatusFolder.exists()) {
            val statusFiles = whatsappStatusFolder.listFiles { file ->
                file.extension == "jpg" || file.extension == "mp4" || file.extension == "mp3" || file.extension == "opus"
            }
            if (statusFiles != null) {
                statuses.addAll(statusFiles)
            } else {
                Log.d("WhatsAppStatus", "No files found")
            }
        } else {
            Log.e("WhatsAppStatus", "Folder not found")
        }
    }
    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 50.dp),
        contentPadding = PaddingValues(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(statuses) { statusFile ->
            when (statusFile.extension) {
                "jpg" -> {
                    val bitmap = loadImageBitmap(statusFile)
                    if (bitmap != null) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(1f)
                        ) {
                            Image(
                                bitmap = bitmap.asImageBitmap(),
                                contentDescription = "WhatsApp Status Image",
                                modifier = Modifier
                                    .fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                }

                /* "mp4" -> {
                     Text("Video: ${statusFile.name}", modifier = Modifier.padding(8.dp))
                 }

                 "mp3", "opus" -> {
                     AudioFileItem(statusFile)
                 }*/
            }
        }
    }
}

fun loadImageBitmap(file: File): Bitmap? {
    return BitmapFactory.decodeFile(file.absolutePath)
}
