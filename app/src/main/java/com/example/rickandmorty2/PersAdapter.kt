package com.example.rickandmorty2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class PersAdapter(private val persons: List<String>) : RecyclerView.Adapter<PersAdapter.PersonViewHolder>() {

    class PersonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val person: ImageView = itemView.findViewById(R.id.h_pres)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pers_item, parent, false)
        return PersonViewHolder(view)
    }

    override fun getItemCount(): Int {
        return persons.size
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
//        val pers = persons[position]

//        Glide.with(holder.person)
//            .load(pers)
//            .into(holder.person)
    }
}