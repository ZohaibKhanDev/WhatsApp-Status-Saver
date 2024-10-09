package com.example.whatsappstatus_saver.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun TermsConditionScreen(navController: NavController) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
            .verticalScroll(scrollState)
    ) {
        Text(
            text = "Terms and Conditions",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = "1. Introduction",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Text(
            text = "By downloading or using the app, you agree to these terms and conditions. Please read them carefully. If you disagree with any part of these terms, you may not access the application.",
            fontSize = 16.sp,
            color = Color.Gray
        )


        Text(
            text = "2. License and Use",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Text(
            text = "We grant you a non-exclusive, non-transferable, limited license to use the WhatsApp Status Saver app in accordance with these terms. You agree not to use the app for any unlawful purpose.",
            fontSize = 16.sp,
            color = Color.Gray
        )

        Text(
            text = "3. User Data and Privacy",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Text(
            text = "We value your privacy. The app does not collect, store, or share any personal information. However, you are responsible for ensuring that any status you save or download complies with the privacy rights of others.",
            fontSize = 16.sp,
            color = Color.Gray
        )


        Text(
            text = "4. Intellectual Property Rights",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Text(
            text = "All intellectual property rights to the app and its contents belong to us or our licensors. You agree not to copy, distribute, or otherwise exploit the app without our written permission.",
            fontSize = 16.sp,
            color = Color.Gray
        )


        Text(
            text = "5. Third-Party Services",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Text(
            text = "The app may use third-party services that declare their own terms and conditions. We are not responsible for the content or practices of these services.",
            fontSize = 16.sp,
            color = Color.Gray
        )



        Text(
            text = "6. Limitation of Liability",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Text(
            text = "We are not responsible for any loss or damage arising out of your use of the app, including but not limited to data loss or damage to your device. You use the app at your own risk.",
            fontSize = 16.sp,
            color = Color.Gray
        )



        Text(
            text = "7. Modifications to Terms",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Text(
            text = "We reserve the right to modify these terms at any time. Changes will be effective upon posting. You are advised to review the terms regularly to stay informed about updates.",
            fontSize = 16.sp,
            color = Color.Gray
        )

        Text(
            text = "8. Governing Law",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Text(
            text = "These terms are governed by and construed in accordance with the laws of your country of residence, without regard to its conflict of law provisions.",
            fontSize = 16.sp,
            color = Color.Gray
        )



        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .padding(top = 24.dp)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
        ) {
            Text(text = "Accept & Go Back", color = Color.White)
        }
    }
}
