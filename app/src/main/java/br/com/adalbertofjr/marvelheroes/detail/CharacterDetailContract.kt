package br.com.adalbertofjr.marvelheroes.detail

interface CharacterDetailContract {

    interface View {
        fun showCharacterData(name: String)
    }

    interface Presenter {
        fun loadDataCharacter(name: String)
    }
}