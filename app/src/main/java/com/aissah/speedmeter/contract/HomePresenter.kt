package com.aissah.speedmeter.contract

import com.aissah.speedmeter.contract.IHomeContract.Presenter
import com.aissah.speedmeter.contract.IHomeContract.View
import com.aissah.speedmeter.dao.InMemorySpeedPortionDAO
import com.aissah.speedmeter.usecase.ISpeedPortionUseCase
import com.aissah.speedmeter.usecase.SpeedPortionUseCase

class HomePresenter(val useCase: ISpeedPortionUseCase = SpeedPortionUseCase(InMemorySpeedPortionDAO())) : Presenter {

  override fun attachView(view: View) {
    TODO("not implemented")
  }

  override fun getLastSpeedPortions() {
    TODO("not implemented")
  }

}