package br.com.adalbertofjr.marvelheroes.repository

import br.com.adalbertofjr.marvelheroes.characters.CharacterViewModel

class Repository : RepositoryContract {
    override fun getCharacters(): List<CharacterViewModel> {
        return listOf(
            CharacterViewModel("Wolwerine"),
            CharacterViewModel("Ciclope"),
            CharacterViewModel("Homem-Aranha"),
            CharacterViewModel("Batman"))
    }
}