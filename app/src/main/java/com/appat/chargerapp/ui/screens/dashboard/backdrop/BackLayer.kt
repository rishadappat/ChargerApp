package com.appat.chargerapp.ui.screens.dashboard.backdrop

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ElectricBolt
import androidx.compose.material.icons.filled.MoreTime
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.MotionLayout
import com.appat.chargerapp.R
import com.appat.chargerapp.ui.customviews.ChargingCircularProgress
import com.appat.chargerapp.ui.customviews.DottedDecorationView
import com.appat.chargerapp.ui.motionscene.ViewRef
import com.appat.chargerapp.ui.motionscene.dashboardMotionScene
import com.appat.chargerapp.ui.theme.AppTheme

@Composable
fun BackLayer(progress: State<Float>) {
    val carImage = painterResource(R.drawable.polestar_3)
    val motionScene = dashboardMotionScene()
    Box(modifier = Modifier
        .fillMaxHeight(AppTheme.dimens.backLayerHeight)
        .fillMaxWidth()) {
        MotionLayout(
            motionScene,
            progress = progress.value,
            Modifier
                .fillMaxSize()
                .align(Alignment.Center)
        ) {
            val fontSize = customFontSize(ViewRef.CarName.name, "textSize")
            DottedDecorationView(modifier = Modifier
                .layoutId(ViewRef.DottedView))
            Image(painter = carImage,
                contentDescription = "carImage",
                modifier = Modifier
                    .layoutId(ViewRef.CarImage.name))
            Row(modifier = Modifier
                .layoutId(ViewRef.ElapsedTime.name),
                verticalAlignment = Alignment.CenterVertically) {
                Icon(modifier = Modifier.size(20.dp),
                    imageVector = Icons.Filled.MoreTime,
                    tint = MaterialTheme.colors.onPrimary,
                    contentDescription = "Charging")
                Text(text = "45 mins more",
                    color = MaterialTheme.colors.onPrimary,
                    fontWeight = FontWeight.Light,
                    fontSize = 16.sp)
            }
            Text(text = "Polestar 3",
                modifier = Modifier
                    .layoutId(ViewRef.CarName.name),
                fontSize = fontSize,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.onPrimary,
                fontWeight = FontWeight.Bold)
            Row(modifier = Modifier
                .layoutId(ViewRef.ChargingPercentageStatus.name),
                verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Charging",
                    modifier = Modifier,
                    color = MaterialTheme.colors.onPrimary,
                    fontWeight = FontWeight.Light,
                    fontSize = 16.sp)
                Icon(modifier = Modifier.size(20.dp),
                    imageVector = Icons.Filled.ElectricBolt,
                    tint = MaterialTheme.colors.onPrimary,
                    contentDescription = "Charging")
                Text(text = "60%",
                    color = MaterialTheme.colors.onPrimary,
                    fontWeight = FontWeight.Light,
                    fontSize = 16.sp)
            }
            ChargingCircularProgress(modifier = Modifier.layoutId(ViewRef.ChargingCircularProgress),
                progress = 0.6f)
        }
    }
}