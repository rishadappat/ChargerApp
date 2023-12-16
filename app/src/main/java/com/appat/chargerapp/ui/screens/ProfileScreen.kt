package com.appat.chargerapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.ListItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Battery5Bar
import androidx.compose.material.icons.filled.LocalTaxi
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.appat.chargerapp.R
import com.appat.chargerapp.ui.customviews.CommonAppBar
import com.appat.chargerapp.ui.theme.changePasswordColor
import com.appat.chargerapp.ui.theme.chargingHistoryColor
import com.appat.chargerapp.ui.theme.logoutColor
import com.appat.chargerapp.ui.theme.myCarsColor
import com.appat.chargerapp.ui.theme.notificationsColor
import com.appat.chargerapp.ui.theme.profileInfoColor

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProfileScreen() {
    val isDark = isSystemInDarkTheme()
    val settingsTitles = arrayOf("My Cars", "Charging History",
        "Profile Information", "Notification Settings", "Change Password", "Logout")
    val settingsIcons = arrayOf(Icons.Default.LocalTaxi, Icons.Default.Battery5Bar,
        Icons.Default.Person, Icons.Default.Notifications, Icons.Default.Password, Icons.AutoMirrored.Filled.Logout)
    val settingsColors = arrayOf(myCarsColor, chargingHistoryColor, profileInfoColor, notificationsColor,
        changePasswordColor, logoutColor)
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            CommonAppBar(title = "Profile")
        }
    ) { padding ->
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(padding),
            verticalArrangement = Arrangement.spacedBy(15.dp)) {
            item {
                Column(modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Spacer(modifier = Modifier.height(40.dp))
                    Image(modifier = Modifier
                        .size(150.dp)
                        .clip(CircleShape)
                        .border(
                            width = 5.dp,
                            shape = CircleShape,
                            color = MaterialTheme.colors.onBackground
                        ),
                        painter = painterResource(R.drawable.profile_pic),
                        contentDescription = "Profile Pic")
                    Spacer(modifier = Modifier.height(40.dp))
                    Text(text = "Rishad Appat",
                        fontWeight = FontWeight.Bold,
                        fontSize = 28.sp,
                        color = MaterialTheme.colors.onBackground)
                    Spacer(modifier = Modifier.height(40.dp))
                }
            }
            itemsIndexed(items = settingsTitles, itemContent = { index, item ->
                val interactionSource = remember { MutableInteractionSource() }
                ListItem(
                    modifier = Modifier
                        .clickable(
                            onClick = {},
                            interactionSource = interactionSource,
                            indication = rememberRipple(
                                color = settingsColors[index]
                            )
                        )
                        .padding(horizontal = 10.dp),
                    text = {
                    Text(text = item, fontWeight = FontWeight.Medium)
                }, icon = {
                    Box(modifier = Modifier
                        .size(50.dp)
                        .background(
                            color = settingsColors[index]
                                .copy(alpha = if (isDark) 0.2f else 0.05f),
                            shape = CircleShape
                        )
                        .padding(10.dp)) {
                        Icon(modifier = Modifier.align(Alignment.Center),
                            imageVector = settingsIcons[index],
                            tint = settingsColors[index],
                            contentDescription = item)
                    }
                })
            })
        }
    }
}