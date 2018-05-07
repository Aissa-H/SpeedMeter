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
  fun storeNothing_retrieveNothing() {
    val getTest = dao.getSpeedPortions().test()
    getTest.assertNoErrors()
    getTest.assertComplete()
    getTest.assertResult(listOf())
  }
}