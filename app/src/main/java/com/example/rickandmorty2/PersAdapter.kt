package com.example.rickandmorty2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickandmorty2.RNM.RetroService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class PersAdapter(private val persons: List<String>) : RecyclerView.Adapter<PersAdapter.PersonViewHolder>() {

    val retrofit = Retrofit.Builder()
        .baseUrl("https://rickandmortyapi.com/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val personApi = retrofit.create(RetroService::class.java)
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
//        CoroutineScope(Dispatchers.IO).launch{
//            val pers = personApi.getPerson(persons[position].toInt())
//
//            Glide.with(holder.person)
//                .load(pers)
//                .into(holder.person)
//        }

    }
}