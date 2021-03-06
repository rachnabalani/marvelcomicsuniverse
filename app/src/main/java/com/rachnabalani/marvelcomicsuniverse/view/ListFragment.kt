package com.rachnabalani.marvelcomicsuniverse.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.rachnabalani.marvelcomicsuniverse.R
import com.rachnabalani.marvelcomicsuniverse.model.Results
import com.rachnabalani.marvelcomicsuniverse.viewmodel.CharacterListViewModel
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment() {

    private lateinit var viewModelCharacter: CharacterListViewModel
    private val listAdapter = CharacterListAdapter(arrayListOf())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        characterList.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = listAdapter
        }

        refreshLayout.setOnRefreshListener {
            characterList.visibility = View.GONE
            errorMessage.visibility = View.GONE
            loadingProgressBar.visibility = View.VISIBLE
            viewModelCharacter.refresh()
            refreshLayout.isRefreshing = false

        }

        //our viewmodel needs to know which view is linked to it, so we attach fragment in the below line. This ensures that view's lifecycle changes doesn't affect the data
        //and ensures that data can be mutable based on fragment changes
        viewModelCharacter = ViewModelProvider(this).get(CharacterListViewModel::class.java)
        //here we are creating an observe, basically asking this view to keep observing the viewModelCharacter data (character variable's data in this case)
        viewModelCharacter.character.observe(viewLifecycleOwner, Observer<List<Results>> {
            it?.let {
                characterList.visibility = View.VISIBLE
                listAdapter.updateCharacterList(it)
            }
        })
        viewModelCharacter.loading.observe(viewLifecycleOwner, Observer<Boolean> {
            loadingProgressBar.visibility = if(it) View.VISIBLE else View.GONE
            if(it) {
                characterList.visibility = View.GONE
                errorMessage.visibility = View.GONE
            }
        })
        viewModelCharacter.loadError.observe(viewLifecycleOwner, Observer<Boolean> {
            if(it) {
                loadingProgressBar.visibility = View.GONE
                errorMessage.visibility = View.VISIBLE
            }
        })
        viewModelCharacter.refresh()
    }

}