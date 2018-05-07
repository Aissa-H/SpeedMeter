package com.aissah.speedmeter.usecase

import com.aissah.speedmeter.dao.ISpeedPortionDAO
import com.aissah.speedmeter.model.SpeedPortion
import com.nhaarman.mockito_kotlin.mock
import io.reactivex.Single
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mockito.`when`

/**
 * Created on 07/05/18.
 */
class SpeedPortionUseCaseTest {

  val dao:ISpeedPortionDAO = mock()
  val useCase :ISpeedPortionUseCase = SpeedPortionUseCase(dao)

  @Test
  fun shouldReturnSpeedPortion() {
    //arrange
    val speedPortion = SpeedPortion(55f, 35f, 28f)
    `when`(dao.getSpeedPortions()).thenReturn(Single.just(speedPortion))

    //act
    val testObserver = useCase.getSpeedPortions().test()

    //assert
    testObserver.assertNoErrors()
    testObserver.assertComplete()
    testObserver.assertResult(speedPortion)
  }

  @Test
  fun storeSpeedPortions() {
    //arrange
    val speedPortion = SpeedPortion(55f, 35f, 28f)
    `when`(dao.storeSpeedPortions(speedPortion)).thenReturn(Single.just(true))

    //act
    val testObserver = useCase.storeSpeedPortions().test()

    //assert
    testObserver.assertNoErrors()
    testObserver.assertComplete()
    testObserver.assertResult(true)
  }
}