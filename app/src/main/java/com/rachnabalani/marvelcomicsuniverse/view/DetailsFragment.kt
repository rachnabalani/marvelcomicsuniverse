package com.rachnabalani.marvelcomicsuniverse.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.rachnabalani.marvelcomicsuniverse.R
import com.rachnabalani.marvelcomicsuniverse.model.Results
import com.rachnabalani.marvelcomicsuniverse.tools.Utils
import com.rachnabalani.marvelcomicsuniverse.viewmodel.ComicListViewModel
import kotlinx.android.synthetic.main.fragment_details.*
import kotlinx.android.synthetic.main.fragment_list.*

class DetailsFragment : Fragment() {

    private lateinit var viewModelComic: ComicListViewModel
    private val listAdapter = ComicsListAdapter(arrayListOf())
    private var charId :Int = 1011198
    private lateinit var charName: String
    private lateinit var charImageUrl: String
    val utils = Utils()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            charId = DetailsFragmentArgs.fromBundle(it).characterIdForComic
            charImageUrl = DetailsFragmentArgs.fromBundle(it).characterImage
            charName = DetailsFragmentArgs.fromBundle(it).characterName
        }

        comicsRecyclerView.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = listAdapter
        }

        characterImageComicPage.loadImage(charImageUrl,  utils.getProgressDrawable(view.context))
        characterNameComicPage.text = charName

        viewModelComic = ViewModelProvider(this).get(ComicListViewModel::class.java)
        viewModelComic.comicData.observe(viewLifecycleOwner, Observer<List<Results>> {
            it?.let {
                comicsRecyclerView.visibility = View.VISIBLE
                listAdapter.updateComicsList(it)
            }
        })
        viewModelComic.loading.observe(viewLifecycleOwner, Observer<Boolean> {
            loadingProgressBarComics.visibility = if(it) View.VISIBLE else View.GONE
            if(it) {
                comicsRecyclerView.visibility = View.GONE
                errorMessageComics.visibility = View.GONE
            }
        })
        viewModelComic.loadError.observe(viewLifecycleOwner, Observer<Boolean> {
            if(it) {
                comicsRecyclerView.visibility = View.GONE
                errorMessageComics.visibility = View.VISIBLE
            }
        })
        viewModelComic.refresh(charId)
    }

    private fun ImageView.loadImage(uri: String, progressDrawable: CircularProgressDrawable) {
        val options = com.bumptech.glide.request.RequestOptions()
            .placeholder(progressDrawable)
            .error(R.mipmap.ic_launcher_round)
        com.bumptech.glide.Glide.with(context).setDefaultRequestOptions(options).load("$uri").into(this)

    }

}