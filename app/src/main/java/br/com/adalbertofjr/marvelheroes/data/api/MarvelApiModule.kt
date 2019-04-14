package br.com.adalbertofjr.marvelheroes.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MarvelApiModule {
    val BASE_URL = "http://gateway.marvel.com/"

    fun provideClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    fun provideRetrofit(baserURL: String, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baserURL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    fun provideMarvelApiService(): MarvelApi {
        return provideRetrofit(BASE_URL, provideClient()).create(MarvelApi::class.java)
    }
}