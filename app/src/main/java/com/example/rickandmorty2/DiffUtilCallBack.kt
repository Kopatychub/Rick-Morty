package com.example.rickandmorty2

import androidx.recyclerview.widget.DiffUtil
import com.example.rickandmorty2.RNM.CharacterData

class DiffUtilCallBack: DiffUtil.ItemCallback<CharacterData>() {
    override fun areItemsTheSame(oldItem: CharacterData, newItem: CharacterData): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: CharacterData, newItem: CharacterData): Boolean {
        return oldItem.name == newItem.name && oldItem.species == newItem.species
    }


}