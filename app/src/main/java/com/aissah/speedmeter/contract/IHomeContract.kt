package com.aissah.speedmeter.contract

import com.aissah.speedmeter.model.SpeedPortion

/**
 * Created on 08/05/18.
 */
interface IHomeContract {

  interface View{
    fun setSpeed(speed:Float)
    fun onPortionStart()
    fun onPortionEnd()
    fun setLastSpeedPortions(portions:List<SpeedPortion>)
    fun setError(errorMessage:String)
  }

  interface Presenter{
    fun attachView(view:IHomeContract.View)
    fun getLastSpeedPortions()
  }

}