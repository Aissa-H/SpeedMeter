package com.aissah.speedmeter.util

import android.location.Location
import com.google.android.gms.maps.model.LatLng

/**
 * Created on 08/05/18.
 */
fun Float.toKilometerByHour() = (this * 3600) / 1000
fun Location.toLatLng() = LatLng(this.latitude, this.longitude)
fun Double.toKilometers() = this/1000