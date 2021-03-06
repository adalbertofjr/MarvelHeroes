package br.com.adalbertofjr.marvelheroes.data.api

import br.com.adalbertofjr.marvelheroes.data.api.model.Characters
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApi {
    @GET("v1/public/characters")
    fun getCharacters(
        @Query("ts") ts: Long,
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String,
        @Query("offset") offset : Int
    ): Observable<Characters>
}