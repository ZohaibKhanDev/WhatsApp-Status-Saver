package com.example.whatsappstatus_saver.presentation.ui.screens

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddRoad
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material.icons.outlined.ArrowForwardIos
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.PrivacyTip
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SettingScreen(navController: NavController) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("Settings", Context.MODE_PRIVATE)
    var selectedLanguage by remember {
        mutableStateOf(sharedPreferences.getString("selectedLanguage", "English") ?: "English")
    }
    var showLanguageDialog by remember { mutableStateOf(false) }


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = if (selectedLanguage == "Urdu") "ترتیبات" else "Settings",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Outlined.ArrowBack,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0XFF008069))
            )
        }
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 80.dp, start = 16.dp, bottom = 16.dp, end = 16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            RowWithIcon(
                icon = Icons.Outlined.Language,
                title = if (selectedLanguage == "Urdu") "زبان" else "Languages",
                subtitle = if (selectedLanguage == "Urdu") "منتخب زبان: ${selectedLanguage}" else "Default: ${selectedLanguage}",
                onClick = { showLanguageDialog = true }
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = if (selectedLanguage == "Urdu") "دوسرے" else "Others",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0XFF008069),
                modifier = Modifier.align(Alignment.Start)
            )

            RowWithIcon(
                icon = Icons.Outlined.Share,
                title = if (selectedLanguage == "Urdu") "ایپ شیئر کریں" else "Share App",
                subtitle = if (selectedLanguage == "Urdu") "ایپ اپنے دوستوں کے ساتھ شیئر کریں" else "Share app with friends and family"
            )

            RowWithIcon(
                icon = Icons.Outlined.ThumbUp,
                title = if (selectedLanguage == "Urdu") "ہمیں ریٹ کریں" else "Rate Us",
                subtitle = if (selectedLanguage == "Urdu") "براہ کرم ہماری ایپ کو ریٹ کریں" else "Please rate our app"
            )

            RowWithIcon(
                icon = Icons.Outlined.PrivacyTip,
                title = if (selectedLanguage == "Urdu") "پرائیویسی پالیسی" else "Privacy Policy",
                subtitle = if (selectedLanguage == "Urdu") "ہماری پرائیویسی پالیسی پڑھیں" else "Read our privacy policy carefully"
            )

            RowWithIcon(
                icon = Icons.Outlined.AddRoad,
                title = if (selectedLanguage == "Urdu") "شرائط و ضوابط" else "Terms & Conditions",
                subtitle = if (selectedLanguage == "Urdu") "ہماری شرائط و ضوابط پڑھیں" else "Read our terms & conditions carefully"
            )

            RowWithIcon(
                icon = Icons.Outlined.Info,
                title = if (selectedLanguage == "Urdu") "ورژن: 1.0.1" else "Version: 1.0.1",
                subtitle = null
            )
        }


        if (showLanguageDialog) {
            LanguageSelectionDialog(
                selectedLanguage = selectedLanguage,
                onLanguageSelected = { selected ->
                    selectedLanguage = selected
                    sharedPreferences.edit().putString("selectedLanguage", selectedLanguage).apply()
                    showLanguageDialog = false
                },
                onDismissRequest = { showLanguageDialog = false }
            )
        }
    }
}

@Composable
fun RowWithIcon(
    icon: ImageVector,
    title: String,
    subtitle: String?,
    onClick: (() -> Unit)? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = { onClick?.invoke() })
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.weight(1f)
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .border(
                        BorderStroke(1.dp, Color.LightGray),
                        shape = RoundedCornerShape(8.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier.size(24.dp)
                )
            }

            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.DarkGray
                )

                if (subtitle != null) {
                    Text(
                        text = subtitle,
                        color = Color.LightGray,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }

        Icon(
            imageVector = Icons.Outlined.ArrowForwardIos,
            contentDescription = null,
            tint = Color.LightGray,
            modifier = Modifier.size(20.dp)
        )
    }
}

@Composable
fun LanguageSelectionDialog(
    selectedLanguage: String,
    onLanguageSelected: (String) -> Unit,
    onDismissRequest: () -> Unit
) {
    val languages = listOf("English", "Urdu", "Arabic", "Other")

    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = {
            Text(text = "Select Language", fontWeight = FontWeight.Bold, fontSize = 18.sp)
        },
        text = {
            Column {
                languages.forEach { language ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .selectable(
                                selected = (language == selectedLanguage),
                                onClick = { onLanguageSelected(language) }
                            )
                            .padding(8.dp)
                    ) {
                        RadioButton(
                            selected = (language == selectedLanguage),
                            onClick = { onLanguageSelected(language) }
                        )
                        Text(
                            text = language,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismissRequest) {
                Text("OK")
            }
        }
    )
}

