package com.aissah.speedmeter.contract

/**
 * Created on 08/05/18.
 */
interface HomeContract {

  interface View{
    fun setSpeed(speed:Float)
    fun onPortionStart()
    fun onPortionEnd()
  }

  interface Presenter{
    fun attachView(view:HomeContract.View)
  }

}