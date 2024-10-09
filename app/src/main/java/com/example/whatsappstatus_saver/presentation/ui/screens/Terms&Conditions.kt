package com.example.whatsappstatus_saver.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
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
            .background(Color(0xFFF5F5F5))
            .padding(16.dp)
            .verticalScroll(scrollState)
    ) {
        Text(
            text = "Terms and Conditions",
            fontSize = 28.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color(0xFF1A237E),
            modifier = Modifier.padding(bottom = 16.dp)
        )


        TermsSection(
            title = "1. Introduction",
            content = "By downloading or using the app, you agree to these terms and conditions. Please read them carefully. If you disagree with any part of these terms, you may not access the application."
        )

        TermsSection(
            title = "2. License and Use",
            content = "We grant you a non-exclusive, non-transferable, limited license to use the WhatsApp Status Saver app in accordance with these terms. You agree not to use the app for any unlawful purpose."
        )

        TermsSection(
            title = "3. User Data and Privacy",
            content = "We value your privacy. The app does not collect, store, or share any personal information. However, you are responsible for ensuring that any status you save or download complies with the privacy rights of others."
        )

        TermsSection(
            title = "4. Intellectual Property Rights",
            content = "All intellectual property rights to the app and its contents belong to us or our licensors. You agree not to copy, distribute, or otherwise exploit the app without our written permission."
        )

        TermsSection(
            title = "5. Third-Party Services",
            content = "The app may use third-party services that declare their own terms and conditions. We are not responsible for the content or practices of these services."
        )

        TermsSection(
            title = "6. Limitation of Liability",
            content = "We are not responsible for any loss or damage arising out of your use of the app, including but not limited to data loss or damage to your device. You use the app at your own risk."
        )

        TermsSection(
            title = "7. Modifications to Terms",
            content = "We reserve the right to modify these terms at any time. Changes will be effective upon posting. You are advised to review the terms regularly to stay informed about updates."
        )

        TermsSection(
            title = "8. Governing Law",
            content = "These terms are governed by and construed in accordance with the laws of your country of residence, without regard to its conflict of law provisions."
        )


        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .padding(top = 24.dp)
                .fillMaxWidth()
                .shadow(8.dp, shape = RoundedCornerShape(12.dp)),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1A237E))
        ) {
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .size(20.dp)
                    .padding(end = 8.dp)
            )
            Text(text = "Accept & Go Back", color = Color.White, fontSize = 16.sp)
        }
    }
}

@Composable
fun TermsSection(title: String, content: String) {
    Card(
        colors = CardDefaults.cardColors(Color.White),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .shadow(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1A237E)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = content,
                fontSize = 16.sp,
                color = Color.Gray
            )
        }
    }
}

