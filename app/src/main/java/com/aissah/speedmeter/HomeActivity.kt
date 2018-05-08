package com.aissah.speedmeter

import android.Manifest
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.aissah.speedmeter.contract.HomePresenter
import com.aissah.speedmeter.contract.IHomeContract
import com.aissah.speedmeter.model.SpeedPortion
import com.livinglifetechway.quickpermissions.annotations.WithPermissions
import kotlinx.android.synthetic.main.activity_home.laySpeedPartionDetails
import kotlinx.android.synthetic.main.activity_home.progressBar
import kotlinx.android.synthetic.main.activity_home.speedView
import kotlinx.android.synthetic.main.layout_speedportion_details.tvDistanceTraveled
import kotlinx.android.synthetic.main.layout_speedportion_details.tvMaxSpeed
import kotlinx.android.synthetic.main.layout_speedportion_details.tvSpeedAverage

class HomeActivity : AppCompatActivity(), IHomeContract.View {

  var presenter: IHomeContract.Presenter = HomePresenter()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_home)
    presenter.attachView(this)
    startSpeedMeter()
  }

  @WithPermissions(
      permissions = [(Manifest.permission.ACCESS_FINE_LOCATION), (Manifest.permission.ACCESS_COARSE_LOCATION)]
  )
  fun startSpeedMeter() {
    presenter.startSpeedMeter(this)
  }

  override fun setSpeed(speed: Float) {
    speedView.setSpeedAt(speed)
  }

  override fun onPortionStart() {
    speedView.visibility = View.VISIBLE
    laySpeedPartionDetails.visibility = View.GONE
    progressBar.visibility = View.GONE
  }

  override fun onPortionEnd() {
    speedView.visibility = View.GONE
    laySpeedPartionDetails.visibility = View.VISIBLE
    presenter.getLastSpeedPortions()
    progressBar.visibility = View.VISIBLE
  }

  override fun setLastSpeedPortions(portions: List<SpeedPortion>) {
    //TODO: show a list of all portions

    //for the moment, only show the last one details :
    val (maxSpeed, averageSpeed, distance) = portions.last()
    tvMaxSpeed.text = getString(R.string.max_speed, maxSpeed.toString())
    tvSpeedAverage.text = getString(R.string.speed_average, averageSpeed.toString())
    tvDistanceTraveled.text = getString(R.string.distance_traveled, distance.toString())
    progressBar.visibility = View.GONE
  }

  override fun setError(errorMessage: String) {
    if (errorMessage.isNotEmpty())
      Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
    else
      Toast.makeText(this, R.string.error, Toast.LENGTH_SHORT).show()
  }
}
