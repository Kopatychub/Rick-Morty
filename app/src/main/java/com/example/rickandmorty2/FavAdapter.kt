package com.example.rickandmorty2

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickandmorty2.DB.MainDB
import com.example.rickandmorty2.DB.PersItem
import com.example.rickandmorty2.databinding.CardItemBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FavAdapter (
    val onClickItem: (PersItem?) -> Unit
): RecyclerView.Adapter<FavAdapter.MyViewHolder>() {

    var items = emptyList<PersItem>()

    class MyViewHolder(val binding: CardItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = CardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var item = items[position]

        holder.binding.cNick.text = item.name
        holder.binding.cSpacies.text = item.species
        holder.binding.cStatus.text = item.status
        holder.binding.cOrigin.text = item.origin
        holder.binding.cLocation.text = item.location

        if (item?.status == "Alive") holder.binding.isAlive.setBackgroundResource(R.drawable.c_alive)
        else if (item?.status == "Dead") holder.binding.isAlive.setBackgroundResource(R.drawable.c_dead)
        else if (item?.status == "unknown") holder.binding.isAlive.setBackgroundResource(R.drawable.c_unknown)

//        holder.binding.cImg.setImageBitmap(loadPhoto( path+"/"+item.image.toString()))

        holder.binding.persBtn.setOnClickListener { onClickItem(item)  }
    }

//    private fun loadPhoto(path: String) : Bitmap? {
//        val options = BitmapFactory.Options()
//        options.inPreferredConfig = Bitmap.Config.ARGB_8888
//        return BitmapFactory.decodeFile(path, options)
//    }

    fun update(items: List<PersItem>){
        this.items = items
        notifyDataSetChanged()
    }
}