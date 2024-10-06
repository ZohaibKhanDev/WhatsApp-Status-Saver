package com.example.whatsappstatus_saver.presentation.ui.screens

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Saved(navController: NavController, selectedLanguage: String) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("Settings", Context.MODE_PRIVATE)
    var selectedLanguage by remember {
        mutableStateOf(sharedPreferences.getString("selectedLanguage", "English") ?: "English")
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
                            "Urdu" -> "اسٹیٹس سیو"
                            "Arabic" -> "تم حفظ الحالة"
                            "English" -> "Status Saved"
                            else -> "Status Saved"
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
                            "Urdu" -> "اسٹیٹس سیو"
                            "Arabic" -> "تم حفظ الحالة"
                            "English" -> "Status Saved"
                            else -> "Status Saved"
                        },
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )


                    "Arabic" -> Text(
                        text = when (selectedLanguage) {
                            "Urdu" -> "اسٹیٹس سیو"
                            "Arabic" -> "تم حفظ الحالة"
                            "English" -> "Status Saved"
                            else -> "Status Saved"
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

    }
}

