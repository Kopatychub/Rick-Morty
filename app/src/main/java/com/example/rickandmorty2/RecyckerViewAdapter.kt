package com.example.rickandmorty2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickandmorty2.RNM.CharacterData

class RecyckerViewAdapter: PagedListAdapter<CharacterData, RecyckerViewAdapter.MyViewHolder>(DiffUtilCallBack()) {


    class MyViewHolder(view: View):RecyclerView.ViewHolder(view){
        val tvName: TextView = view.findViewById(R.id.c_nick)
        val tvSpecises: TextView = view.findViewById(R.id.c_spacies)
        val tvStatus: TextView = view.findViewById(R.id.c_status)
        val tvLocation: TextView = view.findViewById(R.id.c_location)
        val tvOrigin: TextView = view.findViewById(R.id.c_origin)
        val tvIndicat: ImageView = view.findViewById(R.id.is_alive)
        val imageThumd: ImageView = view.findViewById(R.id.c_Img)

        val persBtn: LinearLayout = view.findViewById(R.id.pers_btn)



        fun bind(data: CharacterData){
            tvName.text = data.name
            tvSpecises.text = data.species
            tvStatus.text = data.status
            tvOrigin.text = data.origin?.name
            tvLocation.text = data.location?.name

            if (data.status == "Alive") tvIndicat.setBackgroundResource(R.drawable.c_alive)
            else if (data.status == "Dead") tvIndicat.setBackgroundResource(R.drawable.c_dead)
            else if (data.status == "unknown") tvIndicat.setBackgroundResource(R.drawable.c_unknown)

            persBtn.setOnClickListener{
                println(data.name)
            }

            val url = data.image

            Glide.with(imageThumd)
                .load(url)
                .into(imageThumd)

        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.card_item, parent, false)
        return MyViewHolder(inflater)
    }
}