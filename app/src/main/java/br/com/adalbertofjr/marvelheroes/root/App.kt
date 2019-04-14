package br.com.adalbertofjr.marvelheroes.root

import android.app.Application

class App : Application() {
    val component: AppComponent by lazy {
        DaggerAppComponent
            .builder()
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }
}