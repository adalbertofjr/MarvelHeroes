package br.com.adalbertofjr.marvelheroes.detail

import br.com.adalbertofjr.marvelheroes.characters.CharacterViewModel

class CharacterDetailPresenter(private val view: CharacterDetailContract.View) : CharacterDetailContract.Presenter {

    override fun loadDataCharacter(character: CharacterViewModel) {
        view.showCharacterData(character)
    }
}