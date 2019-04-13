package br.com.adalbertofjr.marvelheroes.characters

import br.com.adalbertofjr.marvelheroes.data.api.model.Data
import br.com.adalbertofjr.marvelheroes.repository.Repository

class CharactersPresenter(
    private val view: CharactersContract.View,
    private val repository: Repository
) : CharactersContract.Presenter {
    var offset: Int = 0

    override fun loadCharacters() {
        view.showLoading(true)
        repository.getCharacters(
            object : Repository.OnRepositoryListener {
                override fun onFailure(message: String) {
                    view.showLoading(false)
                    view.showMessage(message)
                }

                override fun onResponse(charactersData: Data?) {
                    var charactersViewModel = mutableListOf<CharacterViewModel>()
                    val result = charactersData?.results
                    if (result != null) {
                        result?.forEach { it ->
                            charactersViewModel.add(
                                CharacterViewModel(
                                    it.name,
                                    it.description,
                                    it.thumbnail.path.plus("/portrait_fantastic.${it.thumbnail.extension}"),
                                    it.thumbnail.path.plus("/landscape_incredible.${it.thumbnail.extension}")
                                )
                            )
                        }
                        offset+= result.size
                    }
                    view.showCharacters(charactersViewModel)
                    view.showLoading(false)
                }
            },
            offset
        )
    }

    override fun onClickCharacterDetail(character: CharacterViewModel) {
        view.showCharacterDetail(character)
    }
}