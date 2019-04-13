package br.com.adalbertofjr.marvelheroes.characters

import br.com.adalbertofjr.marvelheroes.repository.Repository

class CharactersPresenter(
    private val view: CharactersContract.View,
    private val repository: Repository
) : CharactersContract.Presenter {
    override fun loadCharacters() {
        view.showLoading(true)
        view.showCharacters(repository.getCharacters())
        view.showLoading(false)
    }

    override fun onClickCharacterDetail(character: CharacterViewModel) {
        view.showCharacterDetail(character)
    }
}