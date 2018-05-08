package com.aissah.speedmeter.dao

import com.aissah.speedmeter.model.SpeedPortion
import io.reactivex.Single

/**
 * Created on 07/05/18.
 */
interface ISpeedPortionDAO {

  fun getSpeedPortions(): Single<List<SpeedPortion>>

  fun storeSpeedPortions(vararg portions: SpeedPortion): Single<Boolean>

}