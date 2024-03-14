package com.example.rickandmorty2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickandmorty2.RNM.PersonsList
import com.example.rickandmorty2.databinding.CardItemBinding

class RecyckerViewAdapter(
    val onClickItem: (PersonsList?) -> Unit
): PagedListAdapter<PersonsList, RecyckerViewAdapter.MyViewHolder>(DiffUtilCallBack()) {


    class MyViewHolder(val binding: CardItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = CardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var item = getItem(position)

        holder.binding.cNick.text = item?.name
        holder.binding.cSpacies.text = item?.species
        holder.binding.cStatus.text = item?.status
        holder.binding.cOrigin.text = item?.origin?.name
        holder.binding.cLocation.text = item?.location?.name

        Glide.with(holder.binding.cImg)
                .load(item?.image)
                .into(holder.binding.cImg)

        holder.binding.persBtn.setOnClickListener { onClickItem(item) }
    }


}