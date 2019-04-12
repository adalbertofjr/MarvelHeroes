package br.com.adalbertofjr.marvelheroes.characters

class CharactersPresenter(private val view: CharactersContract.View) : CharactersContract.Presenter {
    override fun loadCharacters() {
        view.showLoading(true)
        view.showCharacters()
        view.showLoading(false)
    }

    override fun onClickCharacterDetail() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}