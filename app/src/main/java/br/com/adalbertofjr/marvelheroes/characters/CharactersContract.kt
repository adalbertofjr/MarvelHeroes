package br.com.adalbertofjr.marvelheroes.characters

interface CharactersContract {

    interface View {
        fun showLoading(b: Boolean)
        fun showCharacters(characters: List<CharacterViewModel>)
        fun showCharacterDetail(character: CharacterViewModel)
        fun showMessage(message: String)
    }

    interface Presenter {
        fun loadCharacters()
        fun onClickCharacterDetail(character: CharacterViewModel)
    }
}