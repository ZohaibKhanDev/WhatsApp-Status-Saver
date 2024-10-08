package com.example.whatsappstatus_saver.presentation.ui.screens

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.android.billingclient.api.AcknowledgePurchaseParams
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.BillingClientStateListener
import com.android.billingclient.api.BillingFlowParams
import com.android.billingclient.api.BillingResult
import com.android.billingclient.api.Purchase
import com.android.billingclient.api.SkuDetails
import com.android.billingclient.api.SkuDetailsParams
import com.example.whatsappstatus_saver.R
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPermissionsApi::class, ExperimentalMaterial3Api::class)
@Composable
fun WhatsAppStatusScreen(navController: NavController, selectedLanguage: String) {
    val permissionState =
        rememberPermissionState(permission = android.Manifest.permission.READ_EXTERNAL_STORAGE)

    LaunchedEffect(Unit) {
        permissionState.launchPermissionRequest()
    }

    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("Settings", Context.MODE_PRIVATE)
    var selectedLanguage by remember {
        mutableStateOf(sharedPreferences.getString("selectedLanguage", "English") ?: "English")
    }

    var showPurchase by remember {
        mutableStateOf(false)
    }

    Scaffold(
        topBar = {
            TopAppBar(title = {
                when (selectedLanguage) {
                    "Urdu", "Arabic" -> Image(
                        painter = painterResource(id = R.drawable.premium),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .padding(end = 10.dp)
                            .size(26.dp)
                            .clickable {
                                showPurchase = true
                            }
                    )

                    else -> Text(
                        text = when (selectedLanguage) {
                            "Urdu" -> "اسٹیٹس امیجز"
                            "Arabic" -> "صور الحالة"
                            "English" -> "Status Images"
                            else -> "Status Images"
                        },
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                }
            }, actions = {
                when (selectedLanguage) {
                    "Urdu", "Arabic" -> Text(
                        text = when (selectedLanguage) {
                            "Urdu" -> "اسٹیٹس امیجز"
                            "Arabic" -> "صور الحالة"
                            "English" -> "Status Images"
                            else -> "Status Images"
                        },
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )

                    else -> Image(
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
        if (permissionState.status.isGranted) {
            DisplayWhatsAppStatuses(navController)
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = it.calculateTopPadding()),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "Please grant storage access to view WhatsApp statuses.",
                    fontSize = 21.sp,
                    fontWeight = FontWeight.Medium,
                )
            }
        }

        if (showPurchase) {
            showPremiumFeatureDialog(LocalContext.current) {
                showPurchase = false
            }
        }
    }
}


@Composable
fun showPremiumFeatureDialog(context: Context, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text("Premium Features") },
        text = {
            Column {
                Text("Unlock the premium version for these features:")
                Spacer(modifier = Modifier.height(10.dp))
                Text("• Remove Ads")
                Text("• Access exclusive content")
                Text("• Get priority support")
            }
        },
        confirmButton = {
            TextButton(onClick = {
                Toast.makeText(
                    context,
                    "Purchase simulation - Not a real transaction",
                    Toast.LENGTH_SHORT
                ).show()
                onDismiss()
            }) {
                Text("Get Premium")
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text("Cancel")
            }
        }
    )
}
