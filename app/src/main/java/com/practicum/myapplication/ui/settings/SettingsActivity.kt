package com.practicum.myapplication.ui.settings

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import com.practicum.myapplication.R
import com.practicum.myapplication.ui.item.SettingsItem
import com.practicum.myapplication.ui.item.ThemeSwitchItem

private val SettingsTitleColor = Color(0xFF111827)

@Composable
fun SettingsScreen(
    onNavigateBack: () -> Unit
) {
    val context = LocalContext.current
    var isDarkThemeEnabled by remember { mutableStateOf(false) }

    val shareMessage = stringResource(R.string.share_message, context.packageName)
    val shareChooserTitle = stringResource(R.string.share_chooser_title)
    val devEmail = stringResource(R.string.dev_email)
    val emailSubject = stringResource(R.string.email_subject)
    val emailBody = stringResource(R.string.email_body)
    val emailChooserTitle = stringResource(R.string.email_chooser_title)
    val userAgreementLink = stringResource(R.string.user_agreement_link)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 16.dp)
        ) {

            Icon(
                painter = painterResource(R.drawable.back),
                contentDescription = "Назад",
                modifier = Modifier
                    .size(18.dp)
                    .clickable { onNavigateBack() }
            )

            Spacer(modifier = Modifier.width(24.dp))

            Text(
                text = stringResource(R.string.settings_title),
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                color = SettingsTitleColor
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {

            ThemeSwitchItem(
                trackWidth = 36.dp,
                trackHeight = 14.dp,
                thumbSize = 20.dp,
                isChecked = isDarkThemeEnabled,
                onCheckedChange = { checked ->
                    isDarkThemeEnabled = checked
                }
            )

            SettingsItem(
                iconRes = R.drawable.share,
                text = stringResource(R.string.share_app),
                iconWidth = 18.dp,
                iconHeight = 20.dp
            ) {
                val shareIntent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, shareMessage)
                }

                context.startActivity(
                    Intent.createChooser(shareIntent, shareChooserTitle)
                )
            }

            SettingsItem(
                iconRes = R.drawable.support,
                text = stringResource(R.string.write_to_devs),
                iconWidth = 22.dp,
                iconHeight = 20.dp
            ) {
                val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                    data = "mailto:".toUri()
                    putExtra(Intent.EXTRA_EMAIL, arrayOf(devEmail))
                    putExtra(Intent.EXTRA_SUBJECT, emailSubject)
                    putExtra(Intent.EXTRA_TEXT, emailBody)
                }

                context.startActivity(
                    Intent.createChooser(emailIntent, emailChooserTitle)
                )
            }

            SettingsItem(
                iconRes = R.drawable.chevron_right,
                text = stringResource(R.string.user_agreement),
                iconWidth = 9.dp,
                iconHeight = 16.dp
            ) {
                val agreementIntent = Intent(Intent.ACTION_VIEW).apply {
                    data = userAgreementLink.toUri()
                }

                context.startActivity(agreementIntent)
            }
        }
    }
}
