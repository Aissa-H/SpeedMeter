package com.aissah.speedmeter.contract

import com.aissah.speedmeter.model.SpeedPortion
import com.aissah.speedmeter.usecase.ISpeedPortionUseCase
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.Single
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.`when`
import org.mockito.Mockito.any

/**
 * Created on 08/05/18.
 */
class HomePresenterTest{

  val view:IHomeContract.View = mock()
  val useCase:ISpeedPortionUseCase = mock()
  val presenter:IHomeContract.Presenter = HomePresenter(useCase)

  @Test
  fun getLastSpeedPortions_shouldSetLastSpeedPortions() {
    //arrange
    presenter.attachView(view)
    val speedPortion1 = SpeedPortion(111f, 11f, 111f)
    val speedPortion2 = SpeedPortion(222f, 22f, 222f)
    val result = listOf(speedPortion1, speedPortion2)
    `when`(useCase.getSpeedPortions()).thenReturn(Single.just(result))

    //act
    presenter.getLastSpeedPortions()

    //assert
    verify(useCase).getSpeedPortions()
    verify(view.setLastSpeedPortions(result))
  }

  @Test
  fun getLastSpeedPortions_shouldSetNoSpeedPortions() {
    //arrange
    presenter.attachView(view)
    val result = listOf<SpeedPortion>()
    `when`(useCase.getSpeedPortions()).thenReturn(Single.just(result))

    //act
    presenter.getLastSpeedPortions()

    //assert
    verify(useCase).getSpeedPortions()
    verify(view.setLastSpeedPortions(result))
  }

  @Test
  fun getLastSpeedPortions_shouldSetErrorMessage() {
    //arrange
    presenter.attachView(view)
    `when`(useCase.getSpeedPortions()).thenReturn(Single.error(NullPointerException()))

    //act
    presenter.getLastSpeedPortions()

    //assert
    verify(useCase).getSpeedPortions()
    verify(view.setError(any()))
  }

}