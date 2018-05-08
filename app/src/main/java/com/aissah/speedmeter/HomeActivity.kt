package com.aissah.speedmeter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.aissah.speedmeter.contract.HomePresenter
import com.aissah.speedmeter.contract.IHomeContract
import com.aissah.speedmeter.model.SpeedPortion
import kotlinx.android.synthetic.main.activity_home.speedView

class HomeActivity : AppCompatActivity(), IHomeContract.View {

  var presenter: IHomeContract.Presenter = HomePresenter()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_home)
    presenter.attachView(this)
  }

  override fun setSpeed(speed: Float) {
    speedView.setSpeedAt(speed)
  }

  override fun onPortionStart() {
    speedView.visibility = View.VISIBLE
  }

  override fun onPortionEnd() {
    speedView.visibility = View.GONE
  }

  override fun setLastSpeedPortions(portions: List<SpeedPortion>) {
    TODO("not implemented")
  }

  override fun setError(errorMessage: String) {
    Toast.makeText(this,errorMessage,Toast.LENGTH_SHORT).show()
  }
}
