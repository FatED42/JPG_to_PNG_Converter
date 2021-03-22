package com.example.jpg_to_png_converter.ui.navigation

import com.example.jpg_to_png_converter.mvp.navigation.IScreens
import com.example.jpg_to_png_converter.ui.fragment.FragmentConverter
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens : IScreens {
    override fun converter() = FragmentScreen { FragmentConverter.newInstance() }
}