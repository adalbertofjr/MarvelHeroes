package br.com.adalbertofjr.marvelheroes.util

fun String.textOrUseThis(message: String) =
    if (this.isNotEmpty()) this else message