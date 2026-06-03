package com.practicum.myapplication.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.practicum.myapplication.R
import com.practicum.myapplication.ui.item.MenuItem
import com.practicum.myapplication.ui.theme.PlaylistTheme

private val MainBackgroundColor = Color(0xFF2563EB)

data class MainMenuItem(
    val iconRes: Int,
    val title: String,
    val action: MainMenuAction
)

enum class MainMenuAction {
    SEARCH,
    PLAYLISTS,
    FAVORITES,
    SETTINGS
}

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PlaylistTheme {
                PlaylistHost()
            }
        }
    }
}

@Composable
fun MainScreen(
    onNavigateToSearch: () -> Unit,
    onNavigateToSettings: () -> Unit
) {
    val context = LocalContext.current

    val menuItems = listOf(
        MainMenuItem(
            iconRes = R.drawable.search,
            title = "Поиск",
            action = MainMenuAction.SEARCH
        ),
        MainMenuItem(
            iconRes = R.drawable.library,
            title = "Плейлисты",
            action = MainMenuAction.PLAYLISTS
        ),
        MainMenuItem(
            iconRes = R.drawable.favorite_border,
            title = "Избранное",
            action = MainMenuAction.FAVORITES
        ),
        MainMenuItem(
            iconRes = R.drawable.settings,
            title = "Настройки",
            action = MainMenuAction.SETTINGS
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MainBackgroundColor)
    ) {

        Text(
            text = "Playlist Maker",
            color = Color.White,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(
                start = 20.dp,
                top = 18.dp,
                bottom = 20.dp
            )
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .clip(
                    RoundedCornerShape(
                        topStart = 24.dp,
                        topEnd = 24.dp
                    )
                )
                .background(Color.White)
        ) {

            Spacer(modifier = Modifier.height(12.dp))

            Card(
                modifier = Modifier.padding(horizontal = 20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 0.dp
                )
            ) {
                menuItems.forEach { item ->
                    MenuItem(
                        iconRes = item.iconRes,
                        title = item.title
                    ) {
                        when (item.action) {
                            MainMenuAction.SEARCH -> onNavigateToSearch()

                            MainMenuAction.PLAYLISTS -> {
                                Toast.makeText(
                                    context,
                                    "Раздел «Плейлисты» пока в разработке",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

                            MainMenuAction.FAVORITES -> {
                                Toast.makeText(
                                    context,
                                    "Раздел «Избранное» пока в разработке",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

                            MainMenuAction.SETTINGS -> onNavigateToSettings()
                        }
                    }
                }
            }
        }
    }
}
