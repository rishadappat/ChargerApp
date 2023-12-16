package com.appat.chargerapp.ui.motionscene

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.MotionScene
import com.appat.chargerapp.ui.theme.AppTheme

enum class ViewRef {
    CarImage,
    ElapsedTime,
    CarName,
    ChargingPercentageStatus,
    ChargingCircularProgress,
    DottedView
}

@Composable
fun dashboardMotionScene(): MotionScene {
    val carImageWidth = AppTheme.dimens.carImageWidth
    return MotionScene {
        val carImage = createRefFor(ViewRef.CarImage.name)
        val elapsedTime = createRefFor(ViewRef.ElapsedTime.name)
        val carName = createRefFor(ViewRef.CarName.name)
        val chargingPercentageStatus = createRefFor(ViewRef.ChargingPercentageStatus.name)
        val chargingCircularProgress = createRefFor(ViewRef.ChargingCircularProgress.name)
        val dottedView = createRefFor(ViewRef.DottedView.name)
        val initialConstraintSet = constraintSet {
            constrain(chargingCircularProgress) {
                bottom.linkTo(parent.bottom, -(37.5).dp)
                centerHorizontallyTo(parent)
                alpha = 1f
                scaleX = 1f
                scaleY = 1f
            }
            constrain(carImage) {
                width = Dimension.percent(carImageWidth)
                height = Dimension.percent(0.5f)
                centerTo(parent)
            }
            constrain(elapsedTime) {
                bottom.linkTo(parent.bottom, 10.dp)
                end.linkTo(parent.end, 20.dp)
                alpha = 1f
                scaleX = 1f
                scaleY = 1f
            }
            constrain(carName) {
                top.linkTo(parent.top, 10.dp)
                centerHorizontallyTo(parent)
                customFontSize("textSize", 30.sp)
            }
            constrain(chargingPercentageStatus) {
                bottom.linkTo(parent.bottom, (10).dp)
                start.linkTo(parent.start, 15.dp)
                alpha = 1f
            }
            constrain(dottedView) {
                width = Dimension.matchParent
                top.linkTo(parent.top)
                start.linkTo(parent.start, (0).dp)
                end.linkTo(parent.end, (0).dp)
                alpha = 0.2f
            }
        }
        val finalConstraintSet = constraintSet {
            constrain(chargingCircularProgress) {
                bottom.linkTo(parent.bottom, 50.dp)
                centerHorizontallyTo(parent)
                alpha = 0f
                scaleX = 0f
                scaleY = 0f
            }
            constrain(carImage) {
                width = Dimension.percent(0.4f)
                end.linkTo(parent.end, 20.dp)
                top.linkTo(parent.top, 30.dp)
            }
            constrain(elapsedTime) {
                bottom.linkTo(parent.bottom, 0.dp)
                end.linkTo(parent.end, 15.dp)
                alpha = 0f
            }
            constrain(carName) {
                top.linkTo(parent.top, 30.dp)
                start.linkTo(parent.start, 20.dp)
                customFontSize("textSize", 20.sp)
            }
            constrain(chargingPercentageStatus) {
                top.linkTo(carName.bottom, 10.dp)
                start.linkTo(parent.start, 20.dp)
                alpha = 1f
            }
            constrain(dottedView) {
                top.linkTo(parent.top, -(50).dp)
                width = Dimension.percent(0.5f)
                end.linkTo(parent.end, 0.dp)
                alpha = 0.2f
            }
        }
        defaultTransition(
            from = initialConstraintSet,
            to = finalConstraintSet
        )
    }
}
