package com.example.rickandmorty2

import androidx.recyclerview.widget.DiffUtil
import com.example.rickandmorty2.RNM.PersonsList

class DiffUtilCallBack: DiffUtil.ItemCallback<PersonsList>() {
    override fun areItemsTheSame(oldItem: PersonsList, newItem: PersonsList): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: PersonsList, newItem: PersonsList): Boolean {
        return oldItem.name == newItem.name && oldItem.species == newItem.species
    }


}