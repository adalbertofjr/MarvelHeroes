package br.com.adalbertofjr.marvelheroes.characters

import android.net.ConnectivityManager

interface CharactersContract {

    interface View {
        fun showLoading(b: Boolean)
        fun showCharacters(characters: List<CharacterViewModel>)
        fun showCharacterDetail(character: CharacterViewModel)
        fun showMessage(message: String)
        fun showErrorConnection(isConnected: Boolean)
    }

    interface Presenter {
        fun loadCharacters(connectivityManager: ConnectivityManager)
        fun onClickCharacterDetail(character: CharacterViewModel)
        fun checkConnection(connectivityManager: ConnectivityManager): Boolean
    }
}