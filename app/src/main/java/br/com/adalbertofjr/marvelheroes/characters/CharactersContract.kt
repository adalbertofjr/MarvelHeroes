package br.com.adalbertofjr.marvelheroes.characters

interface CharactersContract {

    interface View{
        fun showLoading(b: Boolean)
        fun showCharacters()
        fun showCharacterDetail()
        fun showMessage()
    }

    interface Presenter{
        fun loadCharacters()
        fun onClickCharacterDetail()
    }
}