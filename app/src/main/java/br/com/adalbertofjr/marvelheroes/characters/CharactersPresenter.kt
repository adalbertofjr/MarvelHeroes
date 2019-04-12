package br.com.adalbertofjr.marvelheroes.characters

class CharactersPresenter(private val view: CharactersContract.View) : CharactersContract.Presenter {
    override fun loadCharacters() {
        view.showLoading(true)
        view.showCharacters()
        view.showLoading(false)
    }

    override fun onClickCharacterDetail(name: String) {
        view.showCharacterDetail(name)
    }
}