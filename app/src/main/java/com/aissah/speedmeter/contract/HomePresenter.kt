package com.aissah.speedmeter.contract

import com.aissah.speedmeter.contract.IHomeContract.Presenter
import com.aissah.speedmeter.contract.IHomeContract.View
import com.aissah.speedmeter.dao.InMemorySpeedPortionDAO
import com.aissah.speedmeter.usecase.ISpeedPortionUseCase
import com.aissah.speedmeter.usecase.SpeedPortionUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomePresenter(val useCase: ISpeedPortionUseCase = SpeedPortionUseCase(InMemorySpeedPortionDAO())) : Presenter {

  lateinit var view:View

  override fun attachView(view: View) {
    this.view=view
  }

  override fun getLastSpeedPortions() {
    useCase.getSpeedPortions()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSuccess { view.setLastSpeedPortions(it) }
        .doOnError { view.setError(it.message!!) }
  }

}