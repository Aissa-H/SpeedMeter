package com.aissah.speedmeter.usecase

import com.aissah.speedmeter.dao.ISpeedPortionDAO
import com.aissah.speedmeter.model.SpeedPortion
import io.reactivex.Single

class SpeedPortionUseCase(val dao: ISpeedPortionDAO) : ISpeedPortionUseCase {
  override fun getSpeedPortions(): Single<List<SpeedPortion>> = dao.getSpeedPortions()

  override fun storeSpeedPortions(vararg portions: SpeedPortion): Single<Boolean> = dao.storeSpeedPortions(*portions)
}