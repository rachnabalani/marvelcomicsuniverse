package com.rachnabalani.marvelcomicsuniverse.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.rachnabalani.marvelcomicsuniverse.data.CharactersApiService
import com.rachnabalani.marvelcomicsuniverse.model.Characters
import com.rachnabalani.marvelcomicsuniverse.model.Results
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class ComicListViewModel(application: Application) : AndroidViewModel(application)  {

    val comicData by lazy { MutableLiveData<ArrayList<Results>>() } //lazy means create only when needed, or dont create ever.
    val characterComic by lazy { MutableLiveData<Characters>() }
    val loadError by lazy { MutableLiveData<Boolean>() }
    val loading by lazy { MutableLiveData<Boolean>() }

    private val disposible = CompositeDisposable()
    private val apiService = CharactersApiService()

    fun refresh(charId: Int) {
        getComics(charId)
    }

    private fun getComics(characterId: Int=1011198) {
        disposible.add(
            apiService.getComics(characterId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<Characters>() {
                    override fun onSuccess(t: Characters) {
                        loadError.value = false
                        loading.value = false
                        characterComic.value = t
                        comicData.value = t.data?.results
                    }
                    override fun onError(e: Throwable) {
                        Log.w("Fetching Comics API", "Error in parsing Comics", e)
                        loading.value = false
                        loadError.value = true
                        characterComic.value = null
                        comicData.value = null
                    }
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposible.clear()
    }


}