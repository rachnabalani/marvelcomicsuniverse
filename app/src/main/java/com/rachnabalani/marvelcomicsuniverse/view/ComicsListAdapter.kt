package com.rachnabalani.marvelcomicsuniverse.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.rachnabalani.marvelcomicsuniverse.R
import com.rachnabalani.marvelcomicsuniverse.model.Results
import com.rachnabalani.marvelcomicsuniverse.tools.Utils
import kotlinx.android.synthetic.main.character_comics_details.view.*

class ComicsListAdapter(private val comicsList : ArrayList<Results>):
    RecyclerView.Adapter<ComicsListViewHolder>() {

    val utils = Utils()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicsListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.character_comics_details, parent, false)
        return ComicsListViewHolder(view)
    }

    override fun getItemCount() = comicsList.size


    override fun onBindViewHolder(holder: ComicsListViewHolder, position: Int) {
        holder.view.textViewComic.text = comicsList[position].title
        holder.view.imageViewComic.loadImage(
            comicsList[position].thumbnail.path,
            comicsList[position].thumbnail.extension,
            utils.getProgressDrawable(holder.view.context)
        )
    }

    fun updateComicsList(newComicsList: List<Results>){
        comicsList.clear()
        comicsList.addAll(newComicsList)
        notifyDataSetChanged()
    }

    private fun ImageView.loadImage(uri: String, extension: String, progressDrawable: CircularProgressDrawable) {
        val options = com.bumptech.glide.request.RequestOptions()
            .placeholder(progressDrawable)
            .error(R.mipmap.ic_launcher_round)
        com.bumptech.glide.Glide.with(context).setDefaultRequestOptions(options).load("$uri.$extension").into(this)

    }
}