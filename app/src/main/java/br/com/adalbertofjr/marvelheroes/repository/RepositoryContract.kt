package br.com.adalbertofjr.marvelheroes.repository

import br.com.adalbertofjr.marvelheroes.data.api.model.Result
import io.reactivex.Observable

interface RepositoryContract {

    fun getCharacters(
        offset: Int
    ): Observable<List<Result>>
}