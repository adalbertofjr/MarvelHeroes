package br.com.adalbertofjr.marvelheroes.characters.injection

import br.com.adalbertofjr.marvelheroes.characters.CharactersContract
import br.com.adalbertofjr.marvelheroes.characters.CharactersPresenter
import br.com.adalbertofjr.marvelheroes.repository.Repository
import dagger.Module
import dagger.Provides

@Module
class CharactersModule(val view: CharactersContract.View) {
    @Provides
    fun provideCharactersPresenter(): CharactersPresenter {
        return CharactersPresenter(view, Repository)
    }
}