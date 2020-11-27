package com.rachnabalani.marvelcomicsuniverse.model

import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.POST

interface CharacterApi {

    @POST()
    fun getCharactersList(@Field("apikey") apikey: String): Single<Characters>
}