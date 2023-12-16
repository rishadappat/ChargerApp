package com.appat.chargerapp.ui.shape

import android.graphics.PointF
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection

class NotchedRoundedCorners(
    private val cutoutRadius: Dp,
    private val cornerRadiusDp: Dp
) : Shape {
    override fun createOutline(
        size: Size, layoutDirection: LayoutDirection, density: Density
    ): Outline {
        val cutoutRadius = density.run { cutoutRadius.toPx() }
        val cornerRadius = density.run { cornerRadiusDp.toPx() }
        return Outline.Generic(Path().apply {
            buildCustomPath(size, cornerRadius, cutoutRadius)
        })
    }
}
fun Path.buildCustomPath(size: Size, cornerRadius: Float, centerCircleRadius: Float) {

    //Referenced from https://proandroiddev.com/how-i-drew-custom-shapes-in-bottom-bar-c4539d86afd7

    val mFirstCurveStartPoint = PointF()
    val mFirstCurveEndPoint = PointF()
    val mFirstCurveControlPoint1 = PointF()
    val mFirstCurveControlPoint2 = PointF()

    //the coordinates of the second curve
    var mSecondCurveStartPoint = PointF()
    val mSecondCurveEndPoint = PointF()
    val mSecondCurveControlPoint1 = PointF()
    val mSecondCurveControlPoint2 = PointF()

    // get width and height of navigation bar
    val mNavigationBarWidth = size.width
    val mNavigationBarHeight = size.height
    // the coordinates (x,y) of the start point before curve
    mFirstCurveStartPoint.set(mNavigationBarWidth / 2 - centerCircleRadius * 2 - centerCircleRadius / 3, 0f)
    // the coordinates (x,y) of the end point after curve
    mFirstCurveEndPoint.set(mNavigationBarWidth / 2, centerCircleRadius + centerCircleRadius / 4)
    // same thing for the second curve
    mSecondCurveStartPoint = mFirstCurveEndPoint
    mSecondCurveEndPoint.set(mNavigationBarWidth / 2 + centerCircleRadius * 2 + centerCircleRadius / 3, 0f)

    // the coordinates (x,y)  of the 1st control point on a cubic curve
    mFirstCurveControlPoint1.set(
        mFirstCurveStartPoint.x + centerCircleRadius + centerCircleRadius / 4,
        mFirstCurveStartPoint.y
    )
    // the coordinates (x,y)  of the 2nd control point on a cubic curve
    mFirstCurveControlPoint2.set(
        mFirstCurveEndPoint.x - centerCircleRadius * 2 + centerCircleRadius,
        mFirstCurveEndPoint.y
    )

    mSecondCurveControlPoint1.set(
        mSecondCurveStartPoint.x + centerCircleRadius * 2 - centerCircleRadius,
        mSecondCurveStartPoint.y
    )
    mSecondCurveControlPoint2.set(
        mSecondCurveEndPoint.x - (centerCircleRadius + centerCircleRadius / 4),
        mSecondCurveEndPoint.y
    )

    apply {
        reset()
        moveTo(0f, 0f)
        arcTo(
            rect = Rect(
                left = 0f,
                top = 0f,
                right = cornerRadius,
                bottom = cornerRadius
            ),
            startAngleDegrees = 180.0f,
            sweepAngleDegrees = 90.0f,
            forceMoveTo = false
        )
        lineTo(mFirstCurveStartPoint.x, mFirstCurveStartPoint.y)

        cubicTo(
            mFirstCurveControlPoint1.x, mFirstCurveControlPoint1.y,
            mFirstCurveControlPoint2.x, mFirstCurveControlPoint2.y,
            mFirstCurveEndPoint.x, mFirstCurveEndPoint.y
        )

        cubicTo(
            mSecondCurveControlPoint1.x, mSecondCurveControlPoint1.y,
            mSecondCurveControlPoint2.x, mSecondCurveControlPoint2.y,
            mSecondCurveEndPoint.x, mSecondCurveEndPoint.y
        )

        lineTo(mNavigationBarWidth, 0f)

        arcTo(
            rect = Rect(
                left = size.width - cornerRadius,
                top = 0f,
                right = size.width,
                bottom = cornerRadius
            ),
            startAngleDegrees = -90.0f,
            sweepAngleDegrees = 90.0f,
            forceMoveTo = false
        )
        lineTo(mNavigationBarWidth, mNavigationBarHeight)
        lineTo(0f, mNavigationBarHeight)
        close()
    }
}