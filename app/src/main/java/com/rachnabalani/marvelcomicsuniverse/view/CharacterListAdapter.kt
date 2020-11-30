package com.rachnabalani.marvelcomicsuniverse.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.rachnabalani.marvelcomicsuniverse.R
import com.rachnabalani.marvelcomicsuniverse.model.Results
import com.rachnabalani.marvelcomicsuniverse.tools.Utils
import kotlinx.android.synthetic.main.item_character.view.*

class CharacterListAdapter(private val characterList : ArrayList<Results>):
    RecyclerView.Adapter<CharacterViewHolder>() {

    val utils = Utils()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_character, parent, false)
        return CharacterViewHolder(
            view
        )
    }

    override fun getItemCount() = characterList.size

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val imageUrl =  characterList[position].thumbnail.path + "." + characterList[position].thumbnail.extension
        holder.view.characterName.text = characterList[position].name
        holder.view.characterListImage.loadImage(
            imageUrl,
            utils.getProgressDrawable(holder.view.context))
        holder.view.characterItemLayout.setOnClickListener {
         val action = ListFragmentDirections.navToDetails(characterList[position].id, characterImage = imageUrl, characterName = characterList[position].name)

            Navigation.findNavController(holder.view).navigate(action)

        }
    }

    fun updateCharacterList(newCharacterList: List<Results>){
        characterList.clear()
        characterList.addAll(newCharacterList)
        notifyDataSetChanged()
    }

}

private fun ImageView.loadImage(uri: String, progressDrawable: CircularProgressDrawable) {
    val options = com.bumptech.glide.request.RequestOptions()
        .placeholder(progressDrawable)
        .error(R.mipmap.ic_launcher_round)
    com.bumptech.glide.Glide.with(context).setDefaultRequestOptions(options).load("$uri").into(this)

}
