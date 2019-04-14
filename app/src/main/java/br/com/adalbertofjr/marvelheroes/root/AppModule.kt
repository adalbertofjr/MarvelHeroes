package br.com.adalbertofjr.marvelheroes.root

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val application: App) {
    @Provides
    @Singleton
    fun provideApp() = application
}