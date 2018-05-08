package com.aissah.speedmeter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.aissah.speedmeter.contract.HomePresenter
import com.aissah.speedmeter.contract.IHomeContract
import com.aissah.speedmeter.model.SpeedPortion

class HomeActivity : AppCompatActivity(), IHomeContract.View {

  var presenter: IHomeContract.Presenter = HomePresenter()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_home)
    presenter.attachView(this)
  }

  override fun setSpeed(speed: Float) {
    TODO("not implemented")
  }

  override fun onPortionStart() {
    TODO("not implemented")
  }

  override fun onPortionEnd() {
    TODO("not implemented")
  }

  override fun setLastSpeedPortions(portions: List<SpeedPortion>) {
    TODO("not implemented")
  }

  override fun setError(errorMessage: String) {
    TODO("not implemented")
  }
}
