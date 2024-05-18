package com.example.a2020102527_main_project_2.ui.utils
/*
    Surname and Initials: Makhooane KE
    Student Number: 2020102527
    Date: 16 May 2024
    Module Code: CSIP6853
*/
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory

/*
    The bitmapDescriptorFromVector function converts a vector drawable resource to a BitmapDescriptor,
     suitable for use as a marker icon in Google Maps. It allows optional tinting of the vector
     drawable and handles the conversion process, providing a convenient way to customize marker
      icons dynamically in Android apps.
 */
fun bitmapDescriptorFromVector(
    context: Context,
    vectorResId: Int,
    tint: Int? = null
): BitmapDescriptor {
    val vectorDrawable = ContextCompat.getDrawable(context, vectorResId)!!
    tint?.let { vectorDrawable.setTint(it) }

    vectorDrawable.setBounds(
        0,
        0,
        vectorDrawable.intrinsicWidth,
        vectorDrawable.intrinsicHeight
    )

    val bitmap = Bitmap.createBitmap(
        vectorDrawable.intrinsicWidth,
        vectorDrawable.intrinsicHeight,
        Bitmap.Config.ARGB_8888
    )

    val canvas = Canvas(bitmap)
    vectorDrawable.draw(canvas)
    return BitmapDescriptorFactory.fromBitmap(bitmap)
}