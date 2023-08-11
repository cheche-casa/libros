package com.example.libros

import android.app.Application
import com.example.libros.data.AppContainer
import com.example.libros.data.DefaultAppContainer

class LibrosApplication : Application() {
    /** AppContainer instance used by the rest of classes to obtain dependencies */
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}

//Hai que engadir ó manifiesto
//android:name=".AnfibiosApplication"

