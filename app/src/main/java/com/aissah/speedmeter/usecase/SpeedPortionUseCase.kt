package com.aissah.speedmeter.usecase

import com.aissah.speedmeter.dao.ISpeedPortionDAO
import com.aissah.speedmeter.model.SpeedPortion
import io.reactivex.Single

class SpeedPortionUseCase(val dao: ISpeedPortionDAO) : ISpeedPortionUseCase {
  override fun getSpeedPortions(): Single<SpeedPortion> {
    TODO("not implemented")
  }

  override fun storeSpeedPortions(vararg portions: SpeedPortion): Single<Boolean> {
    TODO("not implemented")
  }
}