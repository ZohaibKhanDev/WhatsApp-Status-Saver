package com.example.whatsappstatus_saver.presentation.ui.screens

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun Privacy_Policy(navController: NavController) {
    val verticalScroll = rememberScrollState()
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("Settings", Context.MODE_PRIVATE)
    var selectedLanguage by remember {
        mutableStateOf(sharedPreferences.getString("selectedLanguage", "English") ?: "English")
    }
    val privacyPolicies = mapOf(
        "English" to listOf(
            "Privacy Policy for WhatsApp Status Saver",
            "Effective Date: October 8, 2024",
            "Welcome to WhatsApp Status Saver (the “Status Saver”). Your privacy is important to us. This Privacy Policy explains how we collect, use, and protect your personal information when you use our App.",
            "1. Information We Collect:",
            "- Personal Information: We do not collect any personal information that can identify you, such as your name, email address, or phone number.",
            "- Usage Data: We may collect information on how you access and use the App, including your device's IP address and usage patterns.",
            "- Media Files: The App allows you to download and save status updates from WhatsApp, but we do not collect or store any media files on our servers.",
            "2. How We Use Your Information:",
            "- To Provide and Maintain Our App: We use your usage data to maintain and improve the functionality of our App.",
            "- To Monitor Usage: We use usage data to enhance user experience and address issues promptly.",
            "- To Communicate with You: We may send you updates related to the App, but we do not collect personal information for this purpose.",
            "3. How We Share Your Information:",
            "- With Your Consent: We may share your information with third parties if you provide explicit consent.",
            "- Legal Requirements: We may disclose your information if required by law or in response to valid requests by public authorities.",
            "4. Data Security:",
            "The security of your data is important to us. We implement reasonable security procedures to protect your information from unauthorized access.",
            "5. Children's Privacy:",
            "Our App does not address anyone under the age of 13. We do not knowingly collect personally identifiable information from children under 13. If you are a parent or guardian and you are aware that your child has provided us with personal data, please contact us.",
            "If you have any questions about this Privacy Policy, please contact us at: mzkhan9610@gmail.com"
        ),
        "Urdu" to listOf(
            "WhatsApp Status Saver کے لیے رازداری کی پالیسی",
            "موثر تاریخ: 8 اکتوبر 2024",
            "WhatsApp Status Saver (جو \"اسٹیٹس سیور\" کہلاتا ہے) میں خوش آمدید۔ آپ کی رازداری ہمارے لیے اہم ہے۔ یہ رازداری کی پالیسی وضاحت کرتی ہے کہ ہم آپ کی ذاتی معلومات کو کس طرح جمع، استعمال اور تحفظ فراہم کرتے ہیں جب آپ ہماری ایپ استعمال کرتے ہیں۔",
            "1. ہم کون سی معلومات جمع کرتے ہیں:",
            "- ذاتی معلومات: ہم کوئی ذاتی معلومات جمع نہیں کرتے جو آپ کی شناخت کر سکے، جیسے آپ کا نام، ای میل ایڈریس، یا فون نمبر۔",
            "- استعمال کے اعداد و شمار: ہم ایپ کے استعمال کے دوران آپ کے آلہ کا IP پتہ اور استعمال کے نمونے جمع کر سکتے ہیں۔",
            "- میڈیا فائلیں: ایپ آپ کو WhatsApp سے اسٹیٹس اپ ڈیٹس ڈاؤن لوڈ اور محفوظ کرنے کی اجازت دیتی ہے، لیکن ہم اپنے سرورز پر کوئی میڈیا فائلیں جمع یا اسٹور نہیں کرتے۔",
            "2. ہم آپ کی معلومات کا کس طرح استعمال کرتے ہیں:",
            "- اپنی ایپ فراہم کرنے اور برقرار رکھنے کے لیے: ہم آپ کے استعمال کے اعداد و شمار کو اپنی ایپ کی فعالیت کو برقرار رکھنے اور بہتر بنانے کے لیے استعمال کرتے ہیں۔",
            "- استعمال کی نگرانی کے لیے: ہم صارف کے تجربے کو بڑھانے اور مسائل کو فوری طور پر حل کرنے کے لیے استعمال کے اعداد و شمار کو استعمال کرتے ہیں۔",
            "- آپ سے بات چیت کرنے کے لیے: ہم آپ کو ایپ سے متعلق اپ ڈیٹس بھیج سکتے ہیں، لیکن ہم اس مقصد کے لیے کوئی ذاتی معلومات جمع نہیں کرتے۔",
            "3. ہم آپ کی معلومات کا کس طرح شیئر کرتے ہیں:",
            "- آپ کی رضا مندی سے: اگر آپ واضح طور پر رضا مندی فراہم کریں تو ہم آپ کی معلومات کو تیسرے فریق کے ساتھ شیئر کر سکتے ہیں۔",
            "- قانونی تقاضوں: اگر قانون کی جانب سے درکار ہو یا عوامی اتھارٹی کی جانب سے درست درخواستوں کے جواب میں، ہم آپ کی معلومات افشا کر سکتے ہیں۔",
            "4. ڈیٹا سیکیورٹی:",
            "آپ کے ڈیٹا کی سیکیورٹی ہمارے لیے اہم ہے۔ ہم غیر مجاز رسائی سے آپ کی معلومات کی حفاظت کے لیے معقول سیکیورٹی طریقہ کار کو نافذ کرتے ہیں۔",
            "5. بچوں کی رازداری:",
            "ہماری ایپ کسی بھی شخص کے لیے نہیں ہے جس کی عمر 13 سال سے کم ہو۔ ہم 13 سال سے کم عمر بچوں سے جان بوجھ کر شناخت کی قابل معلومات جمع نہیں کرتے۔ اگر آپ والدین یا سرپرست ہیں اور آپ جانتے ہیں کہ آپ کے بچے نے ہمیں ذاتی ڈیٹا فراہم کیا ہے تو براہ کرم ہم سے رابطہ کریں۔",
            "اگر آپ کو اس رازداری کی پالیسی کے بارے میں کوئی سوالات ہیں تو براہ کرم ہم سے اس ای میل پر رابطہ کریں: mzkhan9610@gmail.com"
        ),
        "Arabic" to listOf(
            "سياسة الخصوصية لتطبيق WhatsApp Status Saver",
            "تاريخ السريان: 8 أكتوبر 2024",
            "مرحبًا بك في WhatsApp Status Saver (الذي يُسمى \"Status Saver\"). خصوصيتك مهمة بالنسبة لنا. تشرح سياسة الخصوصية هذه كيف نجمع المعلومات الشخصية الخاصة بك ونستخدمها ونحميها عندما تستخدم تطبيقنا.",
            "1. المعلومات التي نجمعها:",
            "- المعلومات الشخصية: لا نجمع أي معلومات شخصية يمكن أن تحدد هويتك، مثل اسمك أو عنوان بريدك الإلكتروني أو رقم هاتفك.",
            "- بيانات الاستخدام: قد نجمع معلومات حول كيفية وصولك واستخدامك للتطبيق، بما في ذلك عنوان IP الخاص بجهازك وأنماط الاستخدام.",
            "- ملفات الوسائط: يتيح لك التطبيق تنزيل وحفظ تحديثات الحالة من WhatsApp، لكننا لا نجمع أو نخزن أي ملفات وسائط على خوادمنا.",
            "2. كيف نستخدم معلوماتك:",
            "- لتقديم وصيانة تطبيقنا: نستخدم بيانات الاستخدام الخاصة بك للحفاظ على وظائف التطبيق وتحسينها.",
            "- لمراقبة الاستخدام: نستخدم بيانات الاستخدام لتعزيز تجربة المستخدم ومعالجة المشكلات بسرعة.",
            "- للتواصل معك: قد نرسل لك تحديثات تتعلق بالتطبيق، لكننا لا نجمع معلومات شخصية لهذا الغرض.",
            "3. كيف نشارك معلوماتك:",
            "- بموافقتك: قد نشارك معلوماتك مع أطراف ثالثة إذا قدمت موافقة صريحة.",
            "- المتطلبات القانونية: قد نكشف عن معلوماتك إذا طلب ذلك القانون أو استجابةً لطلبات صالحة من السلطات العامة.",
            "4. أمان البيانات:",
            "أمان بياناتك مهم بالنسبة لنا. نحن نطبق إجراءات أمان معقولة لحماية معلوماتك من الوصول غير المصرح به.",
            "5. خصوصية الأطفال:",
            "تطبيقنا لا يتوجه إلى أي شخص يقل عمره عن 13 عامًا. نحن لا نجمع عمدًا معلومات شخصية قابلة للتحديد من الأطفال دون سن 13 عامًا. إذا كنت أحد الوالدين أو الوصي وتعلم أن طفلك قد زودنا ببيانات شخصية، يرجى الاتصال بنا.",
            "إذا كانت لديك أي أسئلة حول سياسة الخصوصية هذه، يرجى الاتصال بنا على: mzkhan9610@gmail.com"
        )
    )

    val selectedPolicy = privacyPolicies[selectedLanguage] ?: privacyPolicies["English"]!!

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(verticalScroll)
            .padding(16.dp)
    ) {
        for (i in selectedPolicy.indices) {
            when (selectedPolicy[i].take(2)) {
                "1." -> ExpandableSection(
                    title = selectedPolicy[i],
                    content = selectedPolicy.subList(i + 1, selectedPolicy.indexOfFirst { it.contains("2.") })
                )
                "2." -> ExpandableSection(
                    title = selectedPolicy[i],
                    content = selectedPolicy.subList(i + 1, selectedPolicy.indexOfFirst { it.contains("3.") })
                )
                "3." -> ExpandableSection(
                    title = selectedPolicy[i],
                    content = selectedPolicy.subList(i + 1, selectedPolicy.indexOfFirst { it.contains("4.") })
                )
                "4." -> ExpandableSection(
                    title = selectedPolicy[i],
                    content = selectedPolicy.subList(i + 1, selectedPolicy.indexOfFirst { it.contains("5.") })
                )
                "5." -> ExpandableSection(
                    title = selectedPolicy[i],
                    content = selectedPolicy.subList(i + 1, selectedPolicy.size)
                )
            }
        }
    }
}

@Composable
fun ExpandableSection(
    title: String,
    content: List<String>,
    initiallyExpanded: Boolean = false,
) {
    var isExpanded by remember { mutableStateOf(initiallyExpanded) }

    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .clickable { isExpanded = !isExpanded }
                .padding(8.dp)
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f), shape = MaterialTheme.shapes.small)
                .padding(8.dp)
        )


        if (isExpanded) {
            for (item in content) {
                Text(
                    text = item,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(start = 16.dp, bottom = 4.dp)
                )
            }
        }
    }
}

