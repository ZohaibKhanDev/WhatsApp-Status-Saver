package com.example.whatsappstatus_saver.presentation.ui.screens

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.media.MediaMetadataRetriever
import android.media.ThumbnailUtils
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.navigation.NavController
import com.example.whatsappstatus_saver.R
import com.example.whatsappstatus_saver.presentation.ui.navigation.Screens
import kotlinx.coroutines.delay
import java.io.File
import java.util.concurrent.TimeUnit

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Videos(navController: NavController, selectedLanguage: String) {
    val statuses = remember { mutableStateListOf<File>() }
    var selectedVideo by remember { mutableStateOf<File?>(null) }
    var isLoading by remember { mutableStateOf(true) }

    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("Settings", Context.MODE_PRIVATE)
    var selectedLanguage by remember {
        mutableStateOf(sharedPreferences.getString("selectedLanguage", "English") ?: "English")
    }

    LaunchedEffect(Unit) {
        isLoading = true
        delay(500)
        val whatsappStatusFolder = File(
            Environment.getExternalStorageDirectory()
                .toString() + "/Android/media/com.whatsapp/WhatsApp/Media/.Statuses"
        )
        if (whatsappStatusFolder.exists()) {
            val statusFiles = whatsappStatusFolder.listFiles { file ->
                file.extension.lowercase() in listOf("mp4")
            }
            if (statusFiles != null) {
                statuses.addAll(statusFiles)
            } else {
                Log.d("WhatsAppStatus", "No video files found")
            }
        } else {
            Log.e("WhatsAppStatus", "Folder not found")
        }
        isLoading = false
    }

    Scaffold(
        topBar = {
            TopAppBar(title = {
                when(selectedLanguage){
                    "Urdu" ->   Image(
                        painter = painterResource(id = R.drawable.premium),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .padding(end = 10.dp)
                            .size(26.dp)
                    )

                    "Arabic" ->  Image(
                        painter = painterResource(id = R.drawable.premium),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .padding(end = 10.dp)
                            .size(26.dp)
                    )

                    else ->    Text(
                        text = when (selectedLanguage) {
                            "Urdu" -> "اسٹیٹس ویڈیوز"
                            "Arabic" -> "فيديوهات الحالة"
                            "English" -> "Status Videos"
                            else -> "Status Videos"
                        },
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                }
            },actions = {
                when (selectedLanguage){
                    "Urdu" ->   Text(
                        text = when (selectedLanguage) {
                            "Urdu" -> "اسٹیٹس ویڈیوز"
                            "Arabic" -> "فيديوهات الحالة"
                            "English" -> "Status Videos"
                            else -> "Status Videos"
                        },
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )


                    "Arabic" -> Text(
                        text = when (selectedLanguage) {
                            "Urdu" -> "اسٹیٹس ویڈیوز"
                            "Arabic" -> "فيديوهات الحالة"
                            "English" -> "Status Videos"
                            else -> "Status Videos"
                        },
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )

                    else ->  Image(
                        painter = painterResource(id = R.drawable.premium),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .padding(end = 10.dp)
                            .size(26.dp)
                    )
                }

            }, colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0XFF008069)))
        },
    ) {

        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else if (selectedVideo != null) {
            VideoPlayer(videoFile = selectedVideo!!,
                onDismiss = { selectedVideo = null },
                onDownloadClick = { videoFile ->
                    downloadVideo(context, videoFile)
                })
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
                    val thumbnail = loadVideoThumbnail(statusFile)
                    val duration = getVideoDuration(statusFile)

                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .clickable {
                            val video = Uri.encode(statusFile.absolutePath)
                            navController.navigate(Screens.VideoDetail.route + "/$video")
                        }) {
                        if (thumbnail != null) {
                            Image(
                                bitmap = thumbnail.asImageBitmap(),
                                contentDescription = "WhatsApp Video Thumbnail",
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        }

                        Icon(
                            imageVector = Icons.Default.PlayArrow,
                            contentDescription = "Play Icon",
                            tint = Color.White,
                            modifier = Modifier
                                .align(Alignment.Center)
                                .size(40.dp)
                        )

                        Text(
                            text = duration,
                            color = Color.White,
                            fontSize = 12.sp,
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .padding(4.dp)
                                .background(
                                    Color.Black.copy(alpha = 0.5f),
                                    shape = RoundedCornerShape(4.dp)
                                )
                                .padding(horizontal = 4.dp, vertical = 2.dp)
                        )
                    }
                }
            }
        }
    }
}


fun downloadVideo(context: Context, file: File) {
    try {
        val downloadsFolder = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
            file.name
        )
        file.copyTo(downloadsFolder, overwrite = true)

        saveFileToPreferences(context, downloadsFolder.absolutePath)

        Toast.makeText(context, "Video saved to Downloads", Toast.LENGTH_SHORT).show()

        val intent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
        intent.data = Uri.fromFile(downloadsFolder)
        context.sendBroadcast(intent)

    } catch (e: Exception) {
        e.printStackTrace()
        Toast.makeText(context, "Failed to download video", Toast.LENGTH_SHORT).show()
    }
}

fun loadVideoThumbnail(file: File): Bitmap? {
    return ThumbnailUtils.createVideoThumbnail(
        file.path, MediaStore.Images.Thumbnails.MINI_KIND
    )
}


fun saveFileToPreferences(context: Context, filePath: String) {
    val sharedPreferences = context.getSharedPreferences("SavedMedia", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()

    val savedFiles = sharedPreferences.getStringSet("savedFiles", mutableSetOf()) ?: mutableSetOf()
    savedFiles.add(filePath)
    editor.putStringSet("savedFiles", savedFiles)
    editor.apply()
}

fun getSavedFilesFromPreferences(context: Context): Set<String> {
    val sharedPreferences = context.getSharedPreferences("SavedMedia", Context.MODE_PRIVATE)
    return sharedPreferences.getStringSet("savedFiles", mutableSetOf()) ?: mutableSetOf()
}



@SuppressLint("DefaultLocale")
fun getVideoDuration(file: File): String {
    val retriever = MediaMetadataRetriever()
    retriever.setDataSource(file.path)
    val duration =
        retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)?.toLong() ?: 0L
    retriever.release()

    val minutes = TimeUnit.MILLISECONDS.toMinutes(duration)
    val seconds = TimeUnit.MILLISECONDS.toSeconds(duration) % 60
    return String.format("%02d:%02d", minutes, seconds)
}

@SuppressLint("OpaqueUnitKey")
@Composable
fun VideoPlayer(videoFile: File, onDismiss: () -> Unit, onDownloadClick: (File) -> Unit) {
    val context = LocalContext.current
    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            setMediaItem(MediaItem.fromUri(Uri.fromFile(videoFile)))
            prepare()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
                .align(Alignment.Center)
        ) {
            AndroidView(
                factory = {
                    PlayerView(context).apply {
                        player = exoPlayer
                    }
                }, modifier = Modifier.fillMaxSize()
            )
        }

        Icon(imageVector = Icons.Default.Download,
            contentDescription = "Download Icon",
            tint = Color.White,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
                .clickable {
                    onDownloadClick(videoFile)
                })
    }

    BackHandler(onBack = onDismiss)

    DisposableEffect(Unit) {
        onDispose {
            exoPlayer.release()
        }
    }
}




