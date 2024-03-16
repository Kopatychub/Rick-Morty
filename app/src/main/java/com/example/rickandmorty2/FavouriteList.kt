package com.example.rickandmorty2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmorty2.DB.MainDB
import com.example.rickandmorty2.databinding.ActivityFavouriteListBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Collections

class FavouriteList : AppCompatActivity() {
    lateinit var binding: ActivityFavouriteListBinding
    lateinit var db: MainDB
    lateinit var favAdapter: FavAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavouriteListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = MainDB.getDataBase(this)

        favAdapter = FavAdapter (
            onClickItem = {
                lifecycleScope.launch(Dispatchers.IO) {
                    val intent = Intent(this@FavouriteList, ShowInfo::class.java)
                    intent.putExtra("isLocal", true)
                    intent.putExtra("name", it?.name)
                    intent.putExtra("id", it?.id)
                    startActivity(intent)
                }}
        )
        binding.recycleView.layoutManager = LinearLayoutManager(this)
        binding.recycleView.adapter = favAdapter

        db.personDao().getAllPerson().observe(this){
            Collections.reverse(it)
//            val path = db.personDao().getPath()[0].toString()
            favAdapter.update(it)


        }
    }
}