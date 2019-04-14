package br.com.adalbertofjr.marvelheroes.root

import br.com.adalbertofjr.marvelheroes.characters.injection.CharactersComponent
import br.com.adalbertofjr.marvelheroes.characters.injection.CharactersModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = arrayOf(
        AppModule::class
    )
)
interface AppComponent {
    fun inject(target: App)
    fun inject(target: CharactersModule): CharactersComponent
}