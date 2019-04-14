package br.com.adalbertofjr.marvelheroes.characters

import br.com.adalbertofjr.marvelheroes.repository.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class CharactersPresenter(
    private val view: CharactersContract.View,
    private val repository: Repository
) : CharactersContract.Presenter {
    var offset: Int = 0


    override fun loadCharacters() {
        var charactersViewModel = mutableListOf<CharacterViewModel>()
        view.showLoading(true)
        repository.getCharacters(offset)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { results ->
                    for (result in results) {
                        charactersViewModel.add(
                            CharacterViewModel(
                                result.name,
                                result.description,
                                result.thumbnail.path.plus("/portrait_fantastic.${result.thumbnail.extension}"),
                                result.thumbnail.path.plus("/landscape_incredible.${result.thumbnail.extension}")
                            )
                        )
                    }
                    offset += results.size
                },
                { e ->
                    e.printStackTrace()
                    view.showLoading(false)},
                {
                    view.showCharacters(charactersViewModel)
                    view.showLoading(false)
                }
            )
    }

    override fun onClickCharacterDetail(character: CharacterViewModel) {
        view.showCharacterDetail(character)
    }
}