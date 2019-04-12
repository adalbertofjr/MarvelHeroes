package br.com.adalbertofjr.marvelheroes.detail

class CharacterDetailPresenter(private val view: CharacterDetailContract.View) : CharacterDetailContract.Presenter {

    override fun loadDataCharacter(name: String) {
        view.showCharacterData(name)
    }
}