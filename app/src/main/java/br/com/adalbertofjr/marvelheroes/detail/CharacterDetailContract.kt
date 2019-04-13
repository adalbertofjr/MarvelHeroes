package br.com.adalbertofjr.marvelheroes.detail

import br.com.adalbertofjr.marvelheroes.characters.CharacterViewModel

interface CharacterDetailContract {

    interface View {
        fun showCharacterData(character: CharacterViewModel)
    }

    interface Presenter {
        fun loadDataCharacter(character: CharacterViewModel)
    }
}