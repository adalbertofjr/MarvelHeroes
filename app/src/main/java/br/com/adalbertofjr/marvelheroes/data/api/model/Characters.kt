package br.com.adalbertofjr.marvelheroes.data.api.model

data class Characters(val data: Data)

data class Data(val offset: Int, val limit: Int, val total: Int, val count: Int, val results: List<Result>)

data class Result(val name: String, val description: String, val thumbnail: Thumbnail)

data class Thumbnail(val path: String, val extension: String)
