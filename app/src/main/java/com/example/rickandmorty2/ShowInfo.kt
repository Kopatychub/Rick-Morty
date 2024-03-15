package com.example.rickandmorty2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickandmorty2.RNM.PersonsList
import com.example.rickandmorty2.RNM.RetroInstance
import com.example.rickandmorty2.RNM.RetroService
import com.example.rickandmorty2.databinding.ActivityShowInfoBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


class ShowInfo : AppCompatActivity() {
    lateinit var binding: ActivityShowInfoBinding
    lateinit var persAdapter: PersAdapter
    lateinit var recyclerView: RecyclerView
    lateinit var images: ArrayList<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val retrofit = Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val personApi = retrofit.create(RetroService::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            val person = personApi.getPerson(getOldItem())
            val locId = person.location?.url?.split("/")
            println(locId)
            val location = personApi.getLocation(locId!![5].toInt())
            runOnUiThread{
                binding.hNick.text = person.name
                binding.hStatus.text = person.status
                binding.hSpacies.text = person.species
                binding.hGender.text = person.gender
                binding.hLocation.text = person.location?.name
                binding.hOrigin.text = person.origin?.name

                Glide.with(binding.hImg)
                    .load(person.image)
                    .into(binding.hImg)

                init(location!!.residents)
            }
        }

    }
    private fun init(presList: List<String>){
        recyclerView = findViewById(R.id.pers_recyc)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)

        persAdapter = PersAdapter(presList)
        recyclerView.adapter = persAdapter
    }

    fun getOldItem(): Int {
        val id = intent.getIntExtra("id", -1)
        return id
    }
}