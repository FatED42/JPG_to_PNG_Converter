package com.example.jpg_to_png_converter.mvp.model

import android.net.Uri
import io.reactivex.rxjava3.core.Completable

interface IConverter {
    fun convert(): Completable
    fun getSourceUri(): Uri
    fun getTargetUri(): Uri
    fun getSourceTextName(): String
    fun getTargetTextName(): String
}