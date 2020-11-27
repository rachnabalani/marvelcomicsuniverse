package com.rachnabalani.marvelcomicsuniverse.data

import com.rachnabalani.marvelcomicsuniverse.model.Characters
import io.reactivex.Single
import retrofit2.http.*

interface CharacterApi {

    @GET("/v1/public/characters")
    fun getCharactersList(
        @Query("limit")limit : Int=100,
        @Query("offset")offset: Int=100,
        @Query("ts") ts : String?,
        @Query("apikey") apikey: String?,
        @Query("hash") hash: String?
    ): Single<Characters>
}