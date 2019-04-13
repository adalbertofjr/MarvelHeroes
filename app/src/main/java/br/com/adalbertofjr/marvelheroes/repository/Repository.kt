package br.com.adalbertofjr.marvelheroes.repository

import br.com.adalbertofjr.marvelheroes.BuildConfig
import br.com.adalbertofjr.marvelheroes.data.api.MarvelApiModule
import br.com.adalbertofjr.marvelheroes.data.api.model.Characters
import br.com.adalbertofjr.marvelheroes.data.api.model.Data
import br.com.adalbertofjr.marvelheroes.util.HashUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object Repository : RepositoryContract {


    override fun getCharacters(listener: OnRepositoryListener, offset: Int) {
        val marvelApi = MarvelApiModule().provideMarvelApiService()
        val ts = HashUtil.getTs()
        val marvelApiCall =
            marvelApi.getCharacters(
                ts,
                BuildConfig.PUBLIC_KEY_API_VALUE,
                HashUtil.generateHash(ts),
                offset
            )

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