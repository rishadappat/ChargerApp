package com.appat.chargerapp.ui.customviews

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Battery4Bar
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Route
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.appat.chargerapp.ui.theme.AppTheme
import com.appat.chargerapp.ui.theme.chargingColor
import com.appat.chargerapp.ui.theme.remainingRangeColor
import com.appat.chargerapp.ui.theme.remainingTimeColor
import com.appat.chargerapp.ui.theme.secondaryText
import com.appat.chargerapp.ui.theme.voltageColor

@Composable
fun ChargingInfoView(modifier: Modifier) {
    LazyRow(modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        content = {
        item {
            ChargingInfoItem(image = Icons.Default.Bolt,
                value = "120 Volt",
                title = "Voltage",
                color = voltageColor)
        }
        item {
            ChargingInfoItem(image = Icons.Default.Battery4Bar,
                value = "50K MAh",
                title = "Capacity",
                color = chargingColor)
        }
        item {
            ChargingInfoItem(image = Icons.Default.Timer,
                value = "20 Mins",
                title = "Remaining",
                color = remainingTimeColor)
        }
        item {
            ChargingInfoItem(image = Icons.Default.Route,
                value = "560 Kms",
                title = "Range",
                color = remainingRangeColor)
        }
    })
}

@Composable
fun ChargingInfoItem(image: ImageVector, value: String, title: String, color: Color) {
    val isDark = isSystemInDarkTheme()
    val interactionSource = remember { MutableInteractionSource() }
    Column(modifier = Modifier
        .defaultMinSize(minWidth = 50.dp)
        .clip(RoundedCornerShape(10.dp))
        .background(color = Color.Transparent,
            shape = RoundedCornerShape(10.dp))
        .clickable(onClick = {},
            interactionSource = interactionSource,
            indication = rememberRipple(
                color = color
            ))
        .padding(20.dp)) {
        Icon(
            modifier = Modifier
                .size(AppTheme.dimens.chargingInfoItemIconSize)
                .background(color = color.copy(alpha = if(isDark) 0.2f else 0.05f),
                    shape = CircleShape)
                .padding(15.dp),
            tint = color,
            imageVector = image,
            contentDescription = title)
        Spacer(modifier = Modifier.height(15.dp))
        Text(text = value,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.onBackground)
        Spacer(modifier = Modifier.height(5.dp))
        Text(text = title,
            fontSize = 14.sp,
            color = secondaryText)
    }
}