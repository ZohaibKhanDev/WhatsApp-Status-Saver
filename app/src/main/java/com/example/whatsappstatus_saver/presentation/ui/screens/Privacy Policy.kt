package com.example.whatsappstatus_saver.presentation.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun Privacy_Policy(navController: NavController) {
    var showInformationCollection by remember { mutableStateOf(true) }
    var showUseOfInformation by remember { mutableStateOf(true) }
    var showDataSecurity by remember { mutableStateOf(true) }
    var showChildrenPrivacy by remember { mutableStateOf(true) }
    val verticalScroll = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(verticalScroll)
            .padding(16.dp)
    ) {
        Text(
            text = "Privacy Policy for WhatsApp Status Saver",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = "Effective Date: October 8, 2024",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Welcome to WhatsApp Status Saver (the “Status Saver”). Your privacy is important to us. This Privacy Policy explains how we collect, use, and protect your personal information when you use our App.",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )


        ExpandableSection(
            title = "1. Information We Collect",
            isExpanded = showInformationCollection,
            onClick = { showInformationCollection = !showInformationCollection }
        ) {
            Column(modifier = Modifier.padding(start = 16.dp)) {
                Text(
                    text = "- Personal Information: We do not collect any personal information that can identify you, such as your name, email address, or phone number.",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "- Usage Data: We may collect information on how you access and use the App, including your device's IP address and usage patterns.",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "- Media Files: The App allows you to download and save status updates from WhatsApp, but we do not collect or store any media files on our servers.",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }


        ExpandableSection(
            title = "2. How We Use Your Information",
            isExpanded = showUseOfInformation,
            onClick = { showUseOfInformation = !showUseOfInformation }
        ) {
            Column(modifier = Modifier.padding(start = 16.dp)) {
                Text(
                    text = "- To Provide and Maintain Our App: We use your usage data to maintain and improve the functionality of our App.",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "- To Monitor Usage: We use usage data to enhance user experience and address issues promptly.",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "- To Communicate with You: We may send you updates related to the App, but we do not collect personal information for this purpose.",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }


        ExpandableSection(
            title = "3. How We Share Your Information",
            isExpanded = showDataSecurity,
            onClick = { showDataSecurity = !showDataSecurity }
        ) {
            Column(modifier = Modifier.padding(start = 16.dp)) {
                Text(
                    text = "- With Your Consent: We may share your information with third parties if you provide explicit consent.",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "- Legal Requirements: We may disclose your information if required by law or in response to valid requests by public authorities.",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }


        ExpandableSection(
            title = "4. Data Security",
            isExpanded = showChildrenPrivacy,
            onClick = { showChildrenPrivacy = !showChildrenPrivacy }
        ) {
            Column(modifier = Modifier.padding(start = 16.dp)) {
                Text(
                    text = "The security of your data is important to us. We implement reasonable security procedures to protect your information from unauthorized access.",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }


        ExpandableSection(
            title = "5. Children's Privacy",
            isExpanded = showChildrenPrivacy,
            onClick = { showChildrenPrivacy = !showChildrenPrivacy }
        ) {
            Column(modifier = Modifier.padding(start = 16.dp)) {
                Text(
                    text = "Our App does not address anyone under the age of 13. We do not knowingly collect personally identifiable information from children under 13. If you are a parent or guardian and you are aware that your child has provided us with personal data, please contact us.",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }


        Text(
            text = "If you have any questions about this Privacy Policy, please contact us at: mzkhan9610@gmail.com",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}

@Composable
fun ExpandableSection(
    title: String,
    isExpanded: Boolean,
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        TextButton(onClick = onClick) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium
            )
        }
        if (isExpanded) {
            content()
        }
    }
}