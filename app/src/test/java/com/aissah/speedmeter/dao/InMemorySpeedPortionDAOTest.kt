package com.aissah.speedmeter.dao

import com.aissah.speedmeter.model.SpeedPortion
import org.junit.Test

import org.junit.Assert.*

/**
 * Created on 07/05/18.
 */
class InMemorySpeedPortionDAOTest {

  val dao = InMemorySpeedPortionDAO()

  @Test
  fun storeNothing_retrieveNothing() {
    val getTest = dao.getSpeedPortions().test()
    getTest.assertNoErrors()
    getTest.assertComplete()
    getTest.assertResult(listOf())
  }

  @Test
  fun storeSpeedPortion_retrieveSpeedPortion() {
    val speedPortion = SpeedPortion(55f, 35f, 28f)
    val storeTest = dao.storeSpeedPortions(speedPortion).test()

    storeTest.assertNoErrors()
    storeTest.assertComplete()
    storeTest.assertResult(true)

    val getTest = dao.getSpeedPortions().test()
    getTest.assertNoErrors()
    getTest.assertComplete()
    getTest.assertResult(listOf(speedPortion))
  }

  @Test
  fun storeSpeedPortions_retrieveSpeedPortions() {
    val speedPortion1 = SpeedPortion(55f, 35f, 28f)
    val speedPortion2 = SpeedPortion(44f, 35f, 28f)
    val speedPortion3 = SpeedPortion(33f, 35f, 28f)
    val speedPortion4 = SpeedPortion(22f, 35f, 28f)
    val storeTest = dao.storeSpeedPortions(speedPortion1,speedPortion2,speedPortion3,speedPortion4).test()

    storeTest.assertNoErrors()
    storeTest.assertComplete()
    storeTest.assertResult(true)

    val getTest = dao.getSpeedPortions().test()
    getTest.assertNoErrors()
    getTest.assertComplete()
    getTest.assertResult(listOf(speedPortion1,speedPortion2,speedPortion3,speedPortion4))
  }

}