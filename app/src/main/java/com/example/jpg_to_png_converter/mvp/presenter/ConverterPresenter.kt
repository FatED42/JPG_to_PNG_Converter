package com.example.jpg_to_png_converter.mvp.presenter

import com.example.jpg_to_png_converter.ConverterLogic
import com.example.jpg_to_png_converter.mvp.view.ConverterView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter

class ConverterPresenter(private val scheduler: Scheduler, private val router: Router) : MvpPresenter<ConverterView>() {

    private var imageConverter: ConverterLogic? = null
    private var disposable: Disposable? = null

    fun btnConvertClick() {
        convertJpg2Png()
    }

    private fun convertJpg2Png() = imageConverter?.let { converter ->
        viewState.showProgress()
        disposable = converter.convert()
            .observeOn(scheduler)
            .subscribe({
                viewState.hideProgress()
                viewState.showSuccess()
                viewState.setImg(converter.getTargetUri())
                viewState.setText(converter.getTargetTextName())

            }, { error ->
                println("onError: ${error.message}")
                viewState.hideProgress()
                viewState.showError()
            })
    }

    fun setData(imageConverter: ConverterLogic) {
        this.imageConverter = imageConverter
        viewState.setImg(imageConverter.getSourceUri())
        viewState.setText(imageConverter.getSourceTextName())
    }

    fun cancelPressed() {
        disposable?.dispose()
        viewState.hideProgress()
        viewState.showCancel()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}