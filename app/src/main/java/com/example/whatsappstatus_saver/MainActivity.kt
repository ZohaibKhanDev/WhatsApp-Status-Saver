package com.example.whatsappstatus_saver

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.database.ContentObserver
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.os.Looper
import android.provider.MediaStore
import android.provider.Settings
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.content.ContextCompat
import com.example.whatsappstatus_saver.presentation.ui.WhatsAppStatusScreen
import com.example.whatsappstatus_saver.ui.theme.WhatsAppStatusSaverTheme
import java.io.File

class MainActivity : ComponentActivity() {
    private var photosItems by mutableStateOf(emptyList<String>())
    private lateinit var manageExternalStorageResultLauncher: ActivityResultLauncher<Intent>
    private lateinit var permissionRequestLauncher: ActivityResultLauncher<String>

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        manageExternalStorageResultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (Environment.isExternalStorageManager()) {
                photosItems = getWhatsAppStatusList(applicationContext)
            }
        }


        permissionRequestLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            if (isGranted) {
                photosItems = getWhatsAppStatusList(applicationContext)
            }
        }

        setContent {
            WhatsAppStatusSaverTheme {
                WhatsAppStatusScreen()
            }
        }


        requestStoragePermission()
    }


    private fun requestStoragePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (Environment.isExternalStorageManager()) {
                photosItems = getWhatsAppStatusList(applicationContext)
            } else {
                val intent = Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION).apply {
                    data = Uri.parse("package:${packageName}")
                }
                manageExternalStorageResultLauncher.launch(intent)
            }
        } else {
            permissionRequestLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
    }


    private val contentObserver = object : ContentObserver(Handler(Looper.getMainLooper())) {
        override fun onChange(selfChange: Boolean) {
            super.onChange(selfChange)
            photosItems = getWhatsAppStatusList(applicationContext)
        }
    }

    override fun onResume() {
        super.onResume()
        contentResolver.registerContentObserver(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            true,
            contentObserver
        )
    }

    override fun onPause() {
        super.onPause()
        contentResolver.unregisterContentObserver(contentObserver)
    }



    fun getWhatsAppStatusList(context: Context): List<String> {
        val mediaList = mutableListOf<String>()
        val projection = arrayOf(
            MediaStore.MediaColumns.DATA,
            MediaStore.MediaColumns.DISPLAY_NAME
        )

        val selectionArgs = arrayOf(
            "%WhatsApp/Media/.Statuses%",
            "%Android/media/com.whatsapp/WhatsApp/Media/.Statuses%"
        )

        val selection = "${MediaStore.Images.Media.RELATIVE_PATH} LIKE ? OR " +
                "${MediaStore.Images.Media.RELATIVE_PATH} LIKE ?"

        val cursor = context.contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            projection,
            selection,
            selectionArgs,
            null
        )

        cursor?.use {
            val dataColumn = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA)
            while (cursor.moveToNext()) {
                val filePath = cursor.getString(dataColumn)
                val file = File(filePath)
                if (file.exists()) {
                    Log.d("MainActivity", "WhatsApp Status Found: $filePath")
                    mediaList.add(filePath)
                } else {
                    Log.e("MainActivity", "File does not exist: $filePath")
                }
            }
        }
        Log.d("MainActivity", "Fetched Statuses: $mediaList")
        return mediaList
    }

}
