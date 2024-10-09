package com.example.whatsappstatus_saver.presentation.ui.screens

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateContentSize
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
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material.icons.outlined.AddRoad
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.ArrowForwardIos
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.PrivacyTip
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.whatsappstatus_saver.presentation.ui.navigation.Screens

@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SettingScreen(
    navController: NavController, selectedLanguage: String, onLanguageChanged: (String) -> Unit
) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("Settings", Context.MODE_PRIVATE)
    var selectedLanguage by remember {
        mutableStateOf(sharedPreferences.getString("selectedLanguage", "English") ?: "English")
    }
    val tittle = context.applicationInfo.packageName

    var rate by remember {
        mutableStateOf(false)
    }
    val pakagename = context.applicationInfo.packageName
    var showLanguageDialog by remember { mutableStateOf(false) }

    val layoutDirection = when (selectedLanguage) {
        "Urdu", "Arabic" -> LayoutDirection.Rtl
        else -> LayoutDirection.Ltr
    }
    val sendIntent = Intent(Intent.ACTION_SEND).apply {
        putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=$pakagename")
        type = "text/plain"
    }
    val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
    val appVersion = packageInfo.versionName
    val shareIntent = Intent.createChooser(sendIntent, null)
    CompositionLocalProvider(LocalLayoutDirection provides layoutDirection) {
        Scaffold(topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = when (selectedLanguage) {
                            "Urdu" -> "ترتیبات"
                            "Arabic" -> "الإعدادات"
                            else -> "Settings"
                        }, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 20.sp
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
        }) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 80.dp, start = 16.dp, bottom = 16.dp, end = 16.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                RowWithIcon(
                    icon = Icons.Outlined.Language, title = when (selectedLanguage) {
                        "Urdu" -> "زبان"
                        "Arabic" -> "اللغات"
                        else -> "Languages"
                    }, subtitle = when (selectedLanguage) {
                        "Urdu" -> "منتخب زبان: ${selectedLanguage}"
                        "Arabic" -> "اللغة المختارة: ${selectedLanguage}"
                        else -> "Selected Language: ${selectedLanguage}"
                    }, onClick = { showLanguageDialog = true }, selectedLanguage = selectedLanguage
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = when (selectedLanguage) {
                        "Urdu" -> "دوسرے"
                        "Arabic" -> "أخرى"
                        else -> "Others"
                    },
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0XFF008069),
                    modifier = Modifier.align(Alignment.Start)
                )


                RowWithIcon(
                    icon = Icons.Outlined.Share, title = when (selectedLanguage) {
                        "Urdu" -> "ایپ شیئر کریں"
                        "Arabic" -> "شارك التطبيق"
                        else -> "Share App"
                    }, subtitle = when (selectedLanguage) {
                        "Urdu" -> "ایپ اپنے دوستوں کے ساتھ شیئر کریں"
                        "Arabic" -> "شارك التطبيق مع الأصدقاء والعائلة"
                        else -> "Share app with friends and family"
                    }, onClick = {
                        context.startActivity(shareIntent)
                    }, selectedLanguage = selectedLanguage
                )


                RowWithIcon(
                    icon = Icons.Outlined.ThumbUp, title = when (selectedLanguage) {
                        "Urdu" -> "ہمیں ریٹ کریں"
                        "Arabic" -> "قيمنا"
                        else -> "Rate Us"
                    }, subtitle = when (selectedLanguage) {
                        "Urdu" -> "براہ کرم ہماری ایپ کو ریٹ کریں"
                        "Arabic" -> "يرجى تقييم تطبيقنا"
                        else -> "Please rate our app"
                    }, onClick = {
                        rate = true
                    }, selectedLanguage = selectedLanguage
                )


                RowWithIcon(
                    icon = Icons.Outlined.PrivacyTip, title = when (selectedLanguage) {
                        "Urdu" -> "پرائیویسی پالیسی"
                        "Arabic" -> "سياسة الخصوصية"
                        else -> "Privacy Policy"
                    }, subtitle = when (selectedLanguage) {
                        "Urdu" -> "ہماری پرائیویسی پالیسی پڑھیں"
                        "Arabic" -> "يرجى قراءة سياسة الخصوصية الخاصة بنا"
                        else -> "Read our privacy policy carefully"
                    }, selectedLanguage = selectedLanguage, onClick = {
                        navController.navigate(Screens.Privacy_Policy.route)
                    }
                )

                RowWithIcon(
                    icon = Icons.Outlined.AddRoad, title = when (selectedLanguage) {
                        "Urdu" -> "شرائط و ضوابط"
                        "Arabic" -> "الشروط والأحكام"
                        else -> "Terms & Conditions"
                    }, subtitle = when (selectedLanguage) {
                        "Urdu" -> "ہماری شرائط و ضوابط پڑھیں"
                        "Arabic" -> "يرجى قراءة الشروط والأحكام"
                        else -> "Read our terms & conditions carefully"
                    }, selectedLanguage = selectedLanguage, onClick = {
                        navController.navigate(Screens.TermsScreen.route)
                    }
                )


                RowWithIcon(
                    icon = Icons.Outlined.Info, title = when (selectedLanguage) {
                        "Urdu" -> "ورژن: $appVersion"
                        "Arabic" -> "الإصدار: $appVersion"
                        else -> "Version: $appVersion"
                    }, selectedLanguage = selectedLanguage, subtitle = null
                )
            }

            if (rate) {
                val starSize = 40.dp
                val starColor = Color(0XFFFFC107)
                var selectedRating by remember { mutableStateOf(0) }
                AlertDialog(
                    onDismissRequest = { rate = false },
                    title = {
                        Text(
                            text = when (selectedLanguage) {
                                "Urdu" -> "ہمیں ریٹ کریں"
                                "Arabic" -> "قيمنا"
                                else -> "Rate Us"
                            },
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                    },
                    text = {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = when (selectedLanguage) {
                                    "Urdu" -> "براہ کرم ہماری ایپ کو ریٹ کریں"
                                    "Arabic" -> "يرجى تقييم تطبيقنا"
                                    else -> "Please rate our app"
                                },
                                fontSize = 16.sp,
                                color = Color.Gray
                            )

                            Row(
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                for (i in 1..5) {
                                    val isSelected = i <= selectedRating
                                    Icon(
                                        imageVector = if (isSelected) Icons.Filled.Star else Icons.Filled.StarBorder,
                                        modifier = Modifier
                                            .size(40.dp)
                                            .clickable { selectedRating = i }
                                            .scale(if (isSelected) 1.2f else 1f)
                                            .animateContentSize(),
                                        contentDescription = null,
                                        tint = if (isSelected) starColor else Color.LightGray
                                    )
                                }
                            }
                        }
                    },
                    confirmButton = {
                        Button(
                            onClick = {
                                rate = false
                                Toast.makeText(context, "Rate Submit", Toast.LENGTH_SHORT).show()
                            },
                            enabled = selectedRating > 0,
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
                        ) {
                            Text(
                                text = when (selectedLanguage) {
                                    "Urdu" -> "جمع کروائیں"
                                    "Arabic" -> "إرسال"
                                    else -> "Submit"
                                },
                                color = Color.White
                            )
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { rate = false }) {
                            Text(
                                text = when (selectedLanguage) {
                                    "Urdu" -> "منسوخ کریں"
                                    "Arabic" -> "إلغاء"
                                    else -> "Cancel"
                                }
                            )
                        }
                    },
                    shape = RoundedCornerShape(16.dp)
                )
            }

            if (showLanguageDialog) {
                LanguageSelectionDialog(selectedLanguage = selectedLanguage,
                    onLanguageSelected = { selected ->
                        selectedLanguage = selected
                        sharedPreferences.edit().putString("selectedLanguage", selectedLanguage)
                            .apply()
                        onLanguageChanged(selected)
                    },
                    onDismissRequest = { showLanguageDialog = false })
            }
        }
    }
}

@Composable
fun RowWithIcon(
    icon: ImageVector,
    title: String,
    subtitle: String?,
    onClick: (() -> Unit)? = null,
    selectedLanguage: String
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
                        BorderStroke(1.dp, Color.LightGray), shape = RoundedCornerShape(8.dp)
                    ), contentAlignment = Alignment.Center
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
            modifier = Modifier
                .size(20.dp)
                .rotate(
                    when (selectedLanguage) {
                        "Urdu" -> 180f
                        "Arabic" -> 180f
                        else -> 0f
                    }

                )
        )
    }
}


@Composable
fun LanguageSelectionDialog(
    selectedLanguage: String,
    onLanguageSelected: (String) -> Unit,
    onDismissRequest: () -> Unit
) {
    var tempSelectedLanguage by remember { mutableStateOf(selectedLanguage) }

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
                                selected = (language == tempSelectedLanguage),
                                onClick = { tempSelectedLanguage = language }
                            )
                            .padding(8.dp)
                    ) {
                        RadioButton(
                            selected = (language == tempSelectedLanguage),
                            onClick = { tempSelectedLanguage = language }
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
            TextButton(onClick = {
                onLanguageSelected(tempSelectedLanguage)
                onDismissRequest()
            }) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismissRequest) {
                Text("Cancel")
            }
        }
    )
}


