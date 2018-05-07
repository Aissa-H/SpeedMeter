package com.aissah.speedmeter.usecase

import com.aissah.speedmeter.dao.ISpeedPortionDAO
import com.aissah.speedmeter.model.SpeedPortion
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.Single
import org.junit.Test
import org.mockito.Mockito.`when`

/**
 * Created on 07/05/18.
 */
class SpeedPortionUseCaseTest {

  var dao: ISpeedPortionDAO = mock()
  val useCase: ISpeedPortionUseCase = SpeedPortionUseCase(dao)

  @Test
  fun getSpeedPortion_shouldReturnSpeedPortion() {
    //arrange
    val speedPortion = SpeedPortion(55f, 35f, 28f)
    `when`(dao.getSpeedPortions()).thenReturn(Single.just(speedPortion))

    //act
    val testObserver = useCase.getSpeedPortions().test()

    //assert
    verify(dao).getSpeedPortions()
    testObserver.assertNoErrors()
    testObserver.assertComplete()
    testObserver.assertResult(speedPortion)
  }

  @Test
  fun storeSpeedPortion_shouldReturnTrue() {
    //arrange
    val speedPortion = SpeedPortion(55f, 35f, 28f)
    `when`(dao.storeSpeedPortions(speedPortion)).thenReturn(Single.just(true))

    //act
    val testObserver = useCase.storeSpeedPortions(speedPortion).test()

    //assert
    verify(dao).storeSpeedPortions(speedPortion)
    testObserver.assertNoErrors()
    testObserver.assertComplete()
    testObserver.assertResult(true)
  }
}