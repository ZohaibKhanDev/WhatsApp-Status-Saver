package com.example.whatsappstatus_saver.presentation.ui.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.CloudDownload
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import java.io.File

@Composable
fun PicDetail(navController: NavController, PicPath: String, selectedLanguage: String) {
    val context = LocalContext.current

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        AsyncImage(
            model = PicPath,
            contentDescription = "",
            contentScale = ContentScale.Fit,
            modifier = Modifier.fillMaxSize()
        )

        Icon(imageVector = Icons.Filled.ArrowBackIosNew,
            contentDescription = "",
            tint = Color.Magenta,
            modifier = Modifier
                .clickable { navController.navigateUp() }
                .align(Alignment.TopStart)
                .padding(6.dp)
        )

        Icon(imageVector = Icons.Filled.CloudDownload,
            contentDescription = "",
            tint = Color.Magenta,
            modifier = Modifier
                .clickable {
                    val file = File(PicPath)
                    downloadImage(context, file)
                }
                .align(Alignment.TopEnd)
                .padding(6.dp)
        )

    }
}


fun downloadImage(context: Context, file: File) {
    try {
        val downloadsFolder = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
            file.name
        )
        file.copyTo(downloadsFolder, overwrite = true)


        saveFileToPreferences(context, downloadsFolder.absolutePath)

        Toast.makeText(context, "Image saved to Downloads", Toast.LENGTH_SHORT).show()

        val intent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
        intent.data = Uri.fromFile(downloadsFolder)
        context.sendBroadcast(intent)

    } catch (e: Exception) {
        e.printStackTrace()
        Toast.makeText(context, "Failed to download image", Toast.LENGTH_SHORT).show()
    }
}

