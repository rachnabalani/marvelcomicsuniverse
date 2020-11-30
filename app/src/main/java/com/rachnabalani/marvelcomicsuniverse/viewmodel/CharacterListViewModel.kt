package com.rachnabalani.marvelcomicsuniverse.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.rachnabalani.marvelcomicsuniverse.model.Characters
import com.rachnabalani.marvelcomicsuniverse.data.CharactersApiService
import com.rachnabalani.marvelcomicsuniverse.model.Results
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class CharacterListViewModel(application: Application) : AndroidViewModel(application) {

    // MutableLiveData lets us continuously change the list data based on whatever we are observing... helps observe any changes and keep updating liveData list
    val character by lazy { MutableLiveData<ArrayList<Results>>() } //lazy means create only when needed, or dont create ever.
    val characters by lazy { MutableLiveData<Characters>() }
    val loadError by lazy { MutableLiveData<Boolean>() }
    val loading by lazy { MutableLiveData<Boolean>() }

    private val disposible = CompositeDisposable()
    private val apiService =
        CharactersApiService()

    fun refresh() {
        getCharacter()
    }

    private fun getCharacter() {
        disposible.add(
            apiService.getCharacters()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<Characters>() {
                    override fun onSuccess(t: Characters) {
                       loadError.value = false
                        loading.value = false
                        characters.value = t
                        character.value = t.data?.results
                    }
                    override fun onError(e: Throwable) {
                        Log.w("Fetching Characters API", "Error in parsing", e)
                        loading.value = false
                        loadError.value = true
                        character.value = null
                        characters.value = null
                    }
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposible.clear()
    }

}