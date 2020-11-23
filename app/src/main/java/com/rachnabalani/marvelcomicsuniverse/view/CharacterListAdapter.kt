package com.rachnabalani.marvelcomicsuniverse.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rachnabalani.marvelcomicsuniverse.R
import com.rachnabalani.marvelcomicsuniverse.model.modelCharacter
import kotlinx.android.synthetic.main.item_character.view.*

class CharacterListAdapter(private val characterList : ArrayList<modelCharacter>):
    RecyclerView.Adapter<CharacterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_character, parent, false)
        return CharacterViewHolder(
            view
        )
    }

    override fun getItemCount() = characterList.size

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.view.characterName.text = characterList[position].name
    }

    fun updateCharacterList(newCharacterList: List<modelCharacter>){
        characterList.clear()
        characterList.addAll(newCharacterList)
        notifyDataSetChanged()
    }

}