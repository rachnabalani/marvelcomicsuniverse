package com.rachnabalani.marvelcomicsuniverse.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.rachnabalani.marvelcomicsuniverse.model.Characters

class ListViewModel(application: Application) : AndroidViewModel(application) {

    val character by lazy { MutableLiveData<List<Characters>>() } //lazy means create only when needed, or dont create ever.
    // MutableLiveData lets us continuously change the list data based on whatever we are observing... helps observe any changes and keep updating liveData list
    val loadError by lazy { MutableLiveData<Boolean>() }
    val loading by lazy { MutableLiveData<Boolean>() }

    fun refresh() {
        getCharacter()
    }

    private fun getCharacter() {
        val a1 = Characters("image1");
        val a2 = Characters("image2");
        val a3 = Characters("image3");
        val a4 = Characters("image4");
        val a5 = Characters("image5");

        val characterList = arrayListOf(a1, a2, a3, a4, a5)
        //these lines gives the observables (for all of our 3 UI elements) a value to observe.. this is where the livadata values are reset/created.
        character.value = characterList
        loadError.value = false
        loading.value = false
    }

}