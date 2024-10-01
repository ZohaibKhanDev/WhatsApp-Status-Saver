package com.example.whatsappstatus_saver.presentation.ui

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import java.io.File

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
                file.extension == "jpg" || file.extension == "mp4"
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

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(statuses) { statusFile ->
            if (statusFile.extension == "jpg") {
                val bitmap = loadImageBitmap(statusFile)
                if (bitmap != null) {
                    Image(
                        bitmap = bitmap.asImageBitmap(),
                        contentDescription = "WhatsApp Status Image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    )
                }
            } else if (statusFile.extension == "mp4") {
                Text("Video: ${statusFile.name}", modifier = Modifier.padding(8.dp))
            }
        }
    }
}

fun loadImageBitmap(file: File): Bitmap? {
    return BitmapFactory.decodeFile(file.absolutePath)
}
