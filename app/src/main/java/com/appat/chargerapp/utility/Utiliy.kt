package com.appat.chargerapp.utility

import android.content.Context
import android.graphics.Bitmap
import android.location.Location
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory

fun getDummyUserLocation(): Location {
    val userLocation = Location("")
    userLocation.latitude = 25.085609
    userLocation.longitude = 55.254897
    return userLocation
}
fun getDistance(locationA: Location, locationB:Location): Float {
    val distance = locationA.distanceTo(locationB)
    return distance/1000
}

fun bitmapDescriptor(
    context: Context,
    @DrawableRes vectorResId: Int
): BitmapDescriptor? {

    // retrieve the actual drawable
    val drawable = ContextCompat.getDrawable(context, vectorResId) ?: return null
    drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
    val bm = Bitmap.createBitmap(
        drawable.intrinsicWidth,
        drawable.intrinsicHeight,
        Bitmap.Config.ARGB_8888
    )

    // draw it onto the bitmap
    val canvas = android.graphics.Canvas(bm)
    drawable.draw(canvas)
    return BitmapDescriptorFactory.fromBitmap(bm)
}