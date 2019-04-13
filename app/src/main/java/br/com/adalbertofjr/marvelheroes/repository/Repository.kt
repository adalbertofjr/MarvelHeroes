package br.com.adalbertofjr.marvelheroes.repository

import br.com.adalbertofjr.marvelheroes.data.api.MarvelApiModule
import br.com.adalbertofjr.marvelheroes.data.api.model.Characters
import br.com.adalbertofjr.marvelheroes.data.api.model.Data
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object Repository : RepositoryContract {
    override fun getCharacters(listener: OnRepositoryListener) {
        val marvelApi = MarvelApiModule().provideMarvelApiService()
        val marvelApiCall =
            marvelApi.getCharacters(1, "bf39d0742f5d1b7b7698c2bf6a1d23de", "c11c1d8c792911adc6209a76ba0121ab")
        marvelApiCall.enqueue(object : Callback<Characters> {
            override fun onFailure(call: Call<Characters>, t: Throwable) {
                listener.onFailure("Boom! Uma força do mal esta nos impedindo de obter os dados!")
            }

            override fun onResponse(call: Call<Characters>, response: Response<Characters>) {
                if (response.isSuccessful) {
                    listener.onResponse(response.body()?.data)
                } else {
                    listener.onFailure("Boom! Uma força do mal esta nos impedindo de obter os dados!")
                }
            }
        })
    }

    interface OnRepositoryListener {
        fun onFailure(message: String)
        fun onResponse(charactersData: Data?)
    }
}