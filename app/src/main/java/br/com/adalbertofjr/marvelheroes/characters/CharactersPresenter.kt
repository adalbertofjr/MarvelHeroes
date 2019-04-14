package br.com.adalbertofjr.marvelheroes.characters

import android.net.ConnectivityManager
import br.com.adalbertofjr.marvelheroes.repository.Repository
import br.com.adalbertofjr.marvelheroes.util.NetworkUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CharactersPresenter(
    private val view: CharactersContract.View,
    private val repository: Repository
) : CharactersContract.Presenter {
    var offset: Int = 0


    override fun loadCharacters(connectivityManager: ConnectivityManager) {
        view.showLoading(true)
        val checkConnection = checkConnection(connectivityManager)
        if (!checkConnection){
            view.showLoading(false)
            view.showErrorConnection(checkConnection)
            return
        }

        view.showErrorConnection(true)
        var charactersViewModel = mutableListOf<CharacterViewModel>()
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
                    view.showLoading(false)
                },
                {
                    view.showCharacters(charactersViewModel)
                    view.showLoading(false)
                }
            )
    }

    override fun checkConnection(connectivityManager: ConnectivityManager): Boolean {
        return NetworkUtil.isConnected(connectivityManager)
    }

    override fun onClickCharacterDetail(character: CharacterViewModel) {
        view.showCharacterDetail(character)
    }
}