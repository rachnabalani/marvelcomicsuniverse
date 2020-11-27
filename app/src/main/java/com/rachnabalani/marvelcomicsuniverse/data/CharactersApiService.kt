package com.rachnabalani.marvelcomicsuniverse.data

import com.rachnabalani.marvelcomicsuniverse.model.Characters
import com.rachnabalani.marvelcomicsuniverse.tools.Utils
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CharactersApiService {

    val utils = Utils()
    val apiKey = utils.getGenKeyUser()?.get("apikey")
    val ts = utils.getGenKeyUser()?.get("ts")
    val hash = utils.getGenKeyUser()?.get("hash")

    private val api = Retrofit.Builder()
        .baseUrl("http://gateway.marvel.com")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(CharacterApi::class.java)

    fun getCharacters() : Single<Characters> {
        return api.getCharactersList(limit = 100, offset = 100, apikey = apiKey, ts = ts, hash = hash)
    }

}