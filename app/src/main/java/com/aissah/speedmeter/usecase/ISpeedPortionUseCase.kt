package com.aissah.speedmeter.usecase

import com.aissah.speedmeter.model.SpeedPortion
import io.reactivex.Single

/**
 * Created on 07/05/18.
 */
interface ISpeedPortionUseCase {

  fun getSpeedPortions(): Single<List<SpeedPortion>>

  fun storeSpeedPortions(vararg portions: SpeedPortion): Single<Boolean>

}