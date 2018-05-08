package com.aissah.speedmeter

import android.view.View
import com.aissah.speedmeter.contract.IHomeContract
import com.aissah.speedmeter.model.SpeedPortion
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import kotlinx.android.synthetic.main.activity_home.progressBar
import kotlinx.android.synthetic.main.activity_home.speedView
import kotlinx.android.synthetic.main.layout_speedportion_details.tvDistanceTraveled
import kotlinx.android.synthetic.main.layout_speedportion_details.tvMaxSpeed
import kotlinx.android.synthetic.main.layout_speedportion_details.tvSpeedAverage
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.shadows.ShadowToast

/**
 * Created on 08/05/18.
 */
@RunWith(RobolectricTestRunner::class)
class HomeActivityTest {

  val presenter: IHomeContract.Presenter = mock()
  lateinit var homeActivity: HomeActivity

  @Before
  fun setUp() {
    homeActivity = Robolectric.setupActivity(HomeActivity::class.java)
    homeActivity.presenter = presenter
  }

  @Test
  fun onCreate_shouldAttachView() {
    //act
    val buildActivity = Robolectric.buildActivity(HomeActivity::class.java)
    homeActivity = buildActivity.get()
    homeActivity.presenter = presenter
    homeActivity.onCreate(null, null)

    //assert
    verify(presenter).attachView(homeActivity)
  }

  @Test
  fun setSpeed_shouldSetValueToSpeedView() {
    homeActivity.setSpeed(15f)
    assert(homeActivity.speedView.speed == 15f)
  }

  @Test
  fun onPortionStart_shouldShowSpeedView() {
    homeActivity.onPortionStart()
    assert(homeActivity.speedView.visibility == View.VISIBLE)
  }

  @Test
  fun onPortionEnd_shouldHideSpeedView() {
    homeActivity.onPortionEnd()
    assert(homeActivity.speedView.visibility == View.GONE)
  }

  @Test
  fun setLastSpeedPortions() {
    val speedPortion = SpeedPortion(50f, 35f, 60f)
    homeActivity.setLastSpeedPortions(listOf(speedPortion))
    assert(homeActivity.tvMaxSpeed.text == homeActivity.getString(R.string.max_speed,speedPortion.maxSpeed.toString()))
    assert(homeActivity.tvSpeedAverage.text == homeActivity.getString(R.string.speed_average, speedPortion.averageSpeed.toString()))
    assert(homeActivity.tvDistanceTraveled.text == homeActivity.getString(R.string.distance_traveled, speedPortion.distance.toString()))
    assert(homeActivity.progressBar.visibility == View.GONE)
  }

  @Test
  fun setError_shouldShowErrorToast() {
    homeActivity.setError("Error")
    assert(ShadowToast.getTextOfLatestToast() == "Error")
  }
}