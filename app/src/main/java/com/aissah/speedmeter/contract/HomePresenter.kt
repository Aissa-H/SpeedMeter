package com.aissah.speedmeter.contract

import android.annotation.SuppressLint
import android.app.Service
import android.content.Context
import android.location.Criteria
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import com.aissah.speedmeter.contract.IHomeContract.Presenter
import com.aissah.speedmeter.contract.IHomeContract.View
import com.aissah.speedmeter.dao.InMemorySpeedPortionDAO
import com.aissah.speedmeter.model.SpeedPortion
import com.aissah.speedmeter.usecase.ISpeedPortionUseCase
import com.aissah.speedmeter.usecase.SpeedPortionUseCase
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.SphericalUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class HomePresenter(val useCase: ISpeedPortionUseCase = SpeedPortionUseCase(
    InMemorySpeedPortionDAO())) : Presenter {

  companion object {
    val TAG = this::class.java.simpleName
  }

  private val UPDATE_INTERVAL_MS = TimeUnit.SECONDS.toMillis(5)
  lateinit var view: View
  private var portionStarted: Boolean = false
  private var locationsOfCurrentCourse = mutableListOf<Location>()

  override fun attachView(view: View) {
    this.view = view
  }

  @SuppressLint("MissingPermission") //QuickPermission is doing the job
  override fun startSpeedMeter(context: Context) {
    val locationManager = context.getSystemService(Service.LOCATION_SERVICE) as LocationManager
    val criteria = Criteria()
    criteria.isSpeedRequired = true
    criteria.speedAccuracy = Criteria.ACCURACY_HIGH
    criteria.verticalAccuracy = Criteria.ACCURACY_HIGH
    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, UPDATE_INTERVAL_MS, 0f,
        object : LocationListener {
          override fun onLocationChanged(location: Location?) {
            location?.let {
              when (it.speed) {
                in 0..10 -> onVeryLowSpeed()
                else -> onNormalSpeed(it)
              }
            }
          }

          override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
            Log.d(TAG, "onStatusChanged")
          }

          override fun onProviderEnabled(provider: String?) {
            Log.d(TAG, "onProviderEnabled")
          }

          override fun onProviderDisabled(provider: String?) {
            Log.d(TAG, "onProviderDisabled")
          }
        })
  }

  private fun onNormalSpeed(location: Location) {
    if (!portionStarted) {
      portionStarted = true
      view.onPortionStart()
    }
    view.setSpeed((location.speed *3600)/1000)
    locationsOfCurrentCourse.add(location)
  }

  private fun onVeryLowSpeed() {
    if (portionStarted) {
      computeAndStoreSpeedPortion()
      view.onPortionEnd()
      portionStarted = false
    }
  }

  private fun computeAndStoreSpeedPortion() {
    val speeds = locationsOfCurrentCourse.map { (it.speed *3600)/1000 }
    val maxSpeed = speeds.max()
    val averageSpeed = speeds.average().toFloat()
    val latlngs = locationsOfCurrentCourse.map { LatLng(it.latitude, it.longitude) }
    val distance = (SphericalUtil.computeLength(latlngs)/1000.0).toFloat()
    val speedPortion = SpeedPortion(maxSpeed!!, averageSpeed, distance)
    useCase.storeSpeedPortions(speedPortion)
  }

  override fun getLastSpeedPortions() {
    useCase.getSpeedPortions()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSuccess { view.setLastSpeedPortions(it) }
        .doOnError { view.setError(it.message!!) }
  }

}