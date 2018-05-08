package com.aissah.speedmeter.contract

import com.aissah.speedmeter.model.SpeedPortion
import com.aissah.speedmeter.usecase.ISpeedPortionUseCase
import com.aissah.speedmeter.util.RxImmediateSchedulerRule
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.`when` as whenever
import org.mockito.Mockito.any
import org.robolectric.RobolectricTestRunner
import org.junit.BeforeClass
import org.junit.ClassRule


/**
 * Created on 08/05/18.
 */

//TODO: fix the tests : https://medium.com/@elye.project/fixing-unit-test-issue-of-rxjava-chain-and-kotlin-746fcef5ade8
class HomePresenterTest{

  val view:IHomeContract.View = mock()
  val useCase:ISpeedPortionUseCase = mock()
  val presenter:IHomeContract.Presenter = HomePresenter(useCase)

  @Rule
  @JvmField
  val schedulers = RxImmediateSchedulerRule()

  @Test
  fun getLastSpeedPortions_shouldSetLastSpeedPortions() {
    //arrange
    presenter.attachView(view)
    val speedPortion1 = SpeedPortion(111f, 11f, 111f)
    val speedPortion2 = SpeedPortion(222f, 22f, 222f)
    val result = listOf(speedPortion1, speedPortion2)
    whenever(useCase.getSpeedPortions()).thenReturn(Single.just(result))

    //act
    presenter.getLastSpeedPortions()

    //assert
    verify(useCase).getSpeedPortions()
    verify(view).setLastSpeedPortions(result)
  }

  @Test
  fun getLastSpeedPortions_shouldSetNoSpeedPortions() {
    //arrange
    presenter.attachView(view)
    val result = listOf<SpeedPortion>()
    whenever(useCase.getSpeedPortions()).thenReturn(Single.just(result))

    //act
    presenter.getLastSpeedPortions()

    //assert
    verify(useCase).getSpeedPortions()
    verify(view).setLastSpeedPortions(emptyList())
  }

  @Test
  fun getLastSpeedPortions_shouldSetErrorMessage() {
    //arrange
    presenter.attachView(view)
    whenever(useCase.getSpeedPortions()).thenReturn(Single.error(NullPointerException()))

    //act
    presenter.getLastSpeedPortions()

    //assert
    verify(useCase).getSpeedPortions()
    verify(view).setError(any())
  }

}