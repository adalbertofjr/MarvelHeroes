package br.com.adalbertofjr.marvelheroes.detail.injection

import br.com.adalbertofjr.marvelheroes.detail.CharacterDetailFragment
import dagger.Subcomponent

@Subcomponent(modules = arrayOf(CharacterDetailModule::class))
interface CharacterDetailComponent {
    fun inject(target: CharacterDetailFragment)
}