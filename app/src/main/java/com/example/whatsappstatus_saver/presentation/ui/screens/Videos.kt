package com.example.whatsappstatus_saver.presentation.ui.screens

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.media.ThumbnailUtils
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FormatAlignCenter
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.navigation.NavController
import java.io.File

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Videos(navController: NavController) {

    val statuses = remember { mutableStateListOf<File>() }
    var selectedVideo by remember { mutableStateOf<File?>(null) }

    LaunchedEffect(Unit) {
        val whatsappStatusFolder = File(
            Environment.getExternalStorageDirectory()
                .toString() + "/Android/media/com.whatsapp/WhatsApp/Media/.Statuses"
        )

        if (whatsappStatusFolder.exists()) {
            val statusFiles = whatsappStatusFolder.listFiles { file ->
                file.extension.lowercase() in listOf("jpg", "jpeg", "png", "mp4", "mp3", "opus")
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

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(
                    text = "Status Videos",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium
                )
            }, actions = {
                Icon(
                    imageVector = Icons.Filled.FormatAlignCenter,
                    contentDescription = "",
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(12.dp))
                Icon(
                    imageVector = Icons.Filled.Send, contentDescription = "", tint = Color.White
                )
            }, colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0XFF008069)))
        },
    ) {

        if (selectedVideo != null) {
            VideoPlayer(videoFile = selectedVideo!!) {
                selectedVideo = null
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = it.calculateTopPadding()),
                contentPadding = PaddingValues(4.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(statuses) { statusFile ->
                    when (statusFile.extension.lowercase()) {
                        "mp4" -> {
                            val thumbnail = loadVideoThumbnail(statusFile)
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .aspectRatio(1f)
                                    .clickable { selectedVideo = statusFile }
                            ) {
                                if (thumbnail != null) {
                                    Image(
                                        bitmap = thumbnail.asImageBitmap(),
                                        contentDescription = "WhatsApp Video Thumbnail",
                                        modifier = Modifier.fillMaxSize(),
                                        contentScale = ContentScale.Crop
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

fun loadVideoThumbnail(file: File): Bitmap? {
    return ThumbnailUtils.createVideoThumbnail(
        file.path,
        MediaStore.Images.Thumbnails.MINI_KIND
    )
}

@SuppressLint("OpaqueUnitKey")
@Composable
fun VideoPlayer(videoFile: File, onDismiss: () -> Unit) {
    val context = LocalContext.current
    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            setMediaItem(MediaItem.fromUri(Uri.fromFile(videoFile)))
            prepare()
        }
    }

    DisposableEffect(
        AndroidView(
            factory = {
                PlayerView(context).apply {
                    player = exoPlayer
                }
            },
            modifier = Modifier.fillMaxSize()
        )
    ) {
        onDispose {
            exoPlayer.release()
        }
    }

    BackHandler(onBack = onDismiss)
}
