package br.com.adalbertofjr.marvelheroes.repository

interface RepositoryContract {

    fun getCharacters(
        listener: Repository.OnRepositoryListener,
        offset: Int
    )
}