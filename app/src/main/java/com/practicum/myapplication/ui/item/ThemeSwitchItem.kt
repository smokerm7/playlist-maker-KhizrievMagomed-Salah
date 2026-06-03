package com.practicum.myapplication.ui.item

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.practicum.myapplication.R

private val SwitchTextColor = Color(0xFF1F2937)
private val UncheckedTrackColor = Color(0xFFE5E7EB)

@Composable
fun ThemeSwitchItem(
    title: String = stringResource(R.string.dark),
    trackWidth: Dp,
    trackHeight: Dp,
    thumbSize: Dp,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    checkedColor: Color = MaterialTheme.colorScheme.primary,
    uncheckedColor: Color = UncheckedTrackColor
) {
    val maxOffset = trackWidth - thumbSize

    val thumbOffset by animateDpAsState(
        targetValue = if (isChecked) maxOffset else 0.dp,
        label = "thumbOffset"
    )

    val trackTint by animateColorAsState(
        targetValue = if (isChecked) checkedColor.copy(alpha = 0.75f) else uncheckedColor,
        label = "trackTint"
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .padding(start = 20.dp, end = 16.dp)
            .clickable {
                onCheckedChange(!isChecked)
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            fontSize = 17.sp,
            color = SwitchTextColor
        )

        Box(
            modifier = Modifier
                .width(trackWidth)
                .height(thumbSize),
            contentAlignment = Alignment.CenterStart
        ) {
            Image(
                painter = painterResource(id = R.drawable.track),
                contentDescription = null,
                modifier = Modifier
                    .width(trackWidth)
                    .height(trackHeight),
                colorFilter = ColorFilter.tint(trackTint)
            )

            Image(
                painter = painterResource(id = R.drawable.knob),
                contentDescription = null,
                modifier = Modifier
                    .offset(x = thumbOffset)
                    .size(thumbSize)
            )
        }
    }
}
