package com.rachnabalani.marvelcomicsuniverse.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.rachnabalani.marvelcomicsuniverse.model.modelCharacter

class ListViewModel(application: Application) : AndroidViewModel(application) {

    val character by lazy { MutableLiveData<List<modelCharacter>>() } //lazy means create only when needed, or dont create ever.
    // MutableLiveData lets us continuously change the list data based on whatever we are observing... helps observe any changes and keep updating liveData list
    val loadError by lazy { MutableLiveData<Boolean>() }
    val loading by lazy { MutableLiveData<Boolean>() }

    fun refresh() {
        getCharacter()
    }

    private fun getCharacter() {
        val a1 = modelCharacter("image1");
        val a2 = modelCharacter("image2");
        val a3 = modelCharacter("image3");
        val a4 = modelCharacter("image4");
        val a5 = modelCharacter("image5");

        val characterList = arrayListOf(a1, a2, a3, a4, a5)
        character.value = characterList
        loadError.value = false
        loading.value = false
    }

}