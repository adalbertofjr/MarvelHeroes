package br.com.adalbertofjr.marvelheroes.repository

import br.com.adalbertofjr.marvelheroes.BuildConfig
import br.com.adalbertofjr.marvelheroes.data.api.MarvelApiModule
import br.com.adalbertofjr.marvelheroes.data.api.model.Result
import br.com.adalbertofjr.marvelheroes.util.HashUtil
import io.reactivex.Observable

object Repository : RepositoryContract {

    override fun getCharacters(offset: Int): Observable<List<Result>> {
        val marvelApi = MarvelApiModule().provideMarvelApiService()
        val ts = HashUtil.getTs()
        return marvelApi.getCharacters(
            ts,
            BuildConfig.PUBLIC_KEY_API_VALUE,
            HashUtil.generateHash(ts),
            offset
        ).map { characters -> characters.data.results }
    }
}