package br.com.adalbertofjr.marvelheroes.repository

import android.util.Log
import br.com.adalbertofjr.marvelheroes.characters.CharacterViewModel
import br.com.adalbertofjr.marvelheroes.data.api.MarvelApiModule
import br.com.adalbertofjr.marvelheroes.data.api.model.Characters
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository : RepositoryContract {
    override fun getCharacters(): List<CharacterViewModel> {
        val marvelApi = MarvelApiModule().provideMarvelApiService()

        val marvelApiCall =
            marvelApi.getCharacters(1, "bf39d0742f5d1b7b7698c2bf6a1d23de", "c11c1d8c792911adc6209a76ba0121ab")

        marvelApiCall.enqueue(object : Callback<Characters> {
            override fun onFailure(call: Call<Characters>, t: Throwable) {
                Log.i("MHAFJR", t.message)
            }

            override fun onResponse(call: Call<Characters>, response: Response<Characters>) {
                Log.i("MHAFJR", response.toString())
            }

        })

        return listOf(
            CharacterViewModel("Wolwerine"),
            CharacterViewModel("Ciclope"),
            CharacterViewModel("Homem-Aranha"),
            CharacterViewModel("Batman")
        )
    }
}