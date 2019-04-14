package br.com.adalbertofjr.marvelheroes.detail.injection

import br.com.adalbertofjr.marvelheroes.detail.CharacterDetailContract
import br.com.adalbertofjr.marvelheroes.detail.CharacterDetailPresenter
import dagger.Module
import dagger.Provides

@Module
class CharacterDetailModule(val view: CharacterDetailContract.View) {
    @Provides
    fun provideCharacterDetailPresenter(): CharacterDetailPresenter {
        return CharacterDetailPresenter(view)
    }
}