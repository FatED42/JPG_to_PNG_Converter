package com.example.jpg_to_png_converter.mvp.presenter

import com.example.jpg_to_png_converter.mvp.navigation.IScreens
import com.example.jpg_to_png_converter.mvp.view.MainView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class MainPresenter(val router: Router, val screens: IScreens) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screens.converter())
    }

    fun backPressed() {
        router.exit()
    }
}