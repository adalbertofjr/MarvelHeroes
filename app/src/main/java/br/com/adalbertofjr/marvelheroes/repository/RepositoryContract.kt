package br.com.adalbertofjr.marvelheroes.repository

import br.com.adalbertofjr.marvelheroes.characters.CharacterViewModel

interface RepositoryContract {

    fun getCharacters(listener: Repository.OnRepositoryListener)
}