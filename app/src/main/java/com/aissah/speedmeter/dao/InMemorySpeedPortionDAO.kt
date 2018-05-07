package com.aissah.speedmeter.dao

import com.aissah.speedmeter.model.SpeedPortion
import io.reactivex.Single

class InMemorySpeedPortionDAO : ISpeedPortionDAO {
  override fun getSpeedPortions(): Single<List<SpeedPortion>> {
    TODO("not implemented")
  }

  override fun storeSpeedPortions(vararg portions: SpeedPortion): Single<Boolean> {
    TODO("not implemented")
  }
}