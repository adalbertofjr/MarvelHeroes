package br.com.adalbertofjr.marvelheroes.characters.injection

import br.com.adalbertofjr.marvelheroes.characters.CharactersFragment
import dagger.Subcomponent

@Subcomponent(modules = [CharactersModule::class])
interface CharactersComponent {
    fun inject(target: CharactersFragment)
}