package com.example.jpg_to_png_converter.ui.activity

import android.os.Bundle
import com.example.jpg_to_png_converter.App
import com.example.jpg_to_png_converter.IBackClickListener
import com.example.jpg_to_png_converter.R
import com.example.jpg_to_png_converter.databinding.ActivityMainBinding
import com.example.jpg_to_png_converter.mvp.presenter.MainPresenter
import com.example.jpg_to_png_converter.mvp.view.MainView
import com.example.jpg_to_png_converter.ui.navigation.AndroidScreens
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(), MainView {
    private val navigator = AppNavigator(this, R.id.container)
    private var ui: ActivityMainBinding? = null

    private val presenter by moxyPresenter {
        MainPresenter(App.instance.router, AndroidScreens())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = ActivityMainBinding.inflate(layoutInflater)
        setContentView(ui?.root)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        App.instance.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        App.instance.navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if (it is IBackClickListener && it.backPressed()) {
                return
            }
        }
        presenter.backPressed()
    }
}