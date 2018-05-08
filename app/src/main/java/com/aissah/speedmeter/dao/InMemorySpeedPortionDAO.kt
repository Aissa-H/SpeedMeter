package com.aissah.speedmeter.dao

import com.aissah.speedmeter.model.SpeedPortion
import io.reactivex.Single

class InMemorySpeedPortionDAO : ISpeedPortionDAO {

  val portions = mutableListOf<SpeedPortion>()

  override fun getSpeedPortions(): Single<List<SpeedPortion>> {
    return Single.just(portions)
  }

  override fun storeSpeedPortions(vararg portions: SpeedPortion): Single<Boolean> {
    this.portions.addAll(portions)
    return Single.just(true)
  }
}