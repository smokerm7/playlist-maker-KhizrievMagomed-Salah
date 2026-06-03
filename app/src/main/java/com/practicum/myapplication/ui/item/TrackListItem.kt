package com.practicum.myapplication.ui.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.practicum.myapplication.R
import com.practicum.myapplication.domain.model.Track

private val TrackTitleColor = Color(0xFF111827)
private val TrackSubtitleColor = Color(0xFF6B7280)
private val TrackArrowColor = Color(0xFF9CA3AF)

@Composable
fun TrackListItem(track: Track) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_music),
            contentDescription = "Обложка трека ${track.trackName}",
            modifier = Modifier
                .size(58.dp)
                .clip(RoundedCornerShape(10.dp))
        )

        Spacer(modifier = Modifier.width(14.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = track.trackName,
                fontWeight = FontWeight.SemiBold,
                fontSize = 17.sp,
                color = TrackTitleColor,
                maxLines = 1
            )

            Text(
                text = "${track.artistName} • ${track.trackTime}",
                fontSize = 12.sp,
                color = TrackSubtitleColor,
                maxLines = 1
            )
        }

        Icon(
            painter = painterResource(id = R.drawable.chevron_right),
            contentDescription = null,
            tint = TrackArrowColor,
            modifier = Modifier.size(22.dp)
        )
    }
}
