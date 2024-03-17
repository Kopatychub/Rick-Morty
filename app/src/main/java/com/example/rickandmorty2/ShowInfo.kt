package com.example.rickandmorty2

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.drawToBitmap
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickandmorty2.DB.MainDB
import com.example.rickandmorty2.DB.PersItem
import com.example.rickandmorty2.RNM.LocationList
import com.example.rickandmorty2.RNM.RetroService
import com.example.rickandmorty2.databinding.ActivityShowInfoBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException


class ShowInfo : AppCompatActivity() {
    lateinit var binding: ActivityShowInfoBinding
    lateinit var persAdapter: PersAdapter
    lateinit var recyclerView: RecyclerView
    lateinit var db: MainDB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = MainDB.getDataBase(this)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val isLocal = intent.getBooleanExtra("isLocal", false)

        val personApi = retrofit.create(RetroService::class.java)
        CoroutineScope(Dispatchers.IO).launch {

            if (!isLocal) {
                println("API")
                val person = personApi.getPerson(getOldItem())
                val locId = person.location?.url?.split("/")
                var location = LocationList()
                if (locId?.size!! > 1) {
                    location = personApi.getLocation(locId!!.last().toInt())
                }

                println(location)
                runOnUiThread {
//                        println(person.id)
                    binding.hNick.text = person.name
                    binding.hStatus.text = person.status
                    binding.hSpacies.text = person.species
                    binding.hGender.text = person.gender
                    binding.hLocation.text = person.location?.name
                    binding.hOrigin.text = person.origin?.name
                    binding.hType.text = person.type
                    if (locId?.size!! > 1) {
                        binding.hLocationType.text = location.type
                        binding.hLocationDic.text = location.dimension
                    }
                    if (person.status == "Alive") binding.isAlive.setBackgroundResource(R.drawable.c_alive)
                    else if (person.status == "Dead") binding.isAlive.setBackgroundResource(R.drawable.c_dead)
                    else if (person.status == "unknown") binding.isAlive.setBackgroundResource(R.drawable.c_unknown)

                    Glide.with(binding.hImg)
                        .load(person.image)
                        .into(binding.hImg)

                    init(location.residents)!!

                }

            } else {
                println("LOCAL")
                val person = db.personDao().getOnePerson(intent.getStringExtra("name"))[0]
                val img = loadPhoto(person.image.toString())

                runOnUiThread {
                    binding.hNick.text = person.name
                    binding.hStatus.text = person.status
                    binding.hSpacies.text = person.species
                    binding.hGender.text = person.gender
                    binding.hLocation.text = person.location
                    binding.hOrigin.text = person.origin
                    binding.hType.text = person.type
                    binding.hFavBtn.setImageResource(R.drawable.star_on)
                    binding.hLocationDic.text = person.location_dim
                    binding.hLocationType.text = person.location_type
                    binding.hImg.setImageBitmap(img)

                    if (person.status == "Alive") binding.isAlive.setBackgroundResource(R.drawable.c_alive)
                    else if (person.status == "Dead") binding.isAlive.setBackgroundResource(R.drawable.c_dead)
                    else if (person.status == "unknown") binding.isAlive.setBackgroundResource(R.drawable.c_unknown)

                    binding.root.removeView(binding.persRecyc)

                }
            }
                println(db.personDao().getOnePerson(binding.hNick.text.toString()).toString())
                if (db.personDao().getOnePerson(binding.hNick.text.toString()).toString() == "[]") {
                        binding.hFavBtn.setImageResource(R.drawable.star_off)
                } else {
                        binding.hFavBtn.setImageResource(R.drawable.star_on)
                }


        }
        binding.hFavBtn.setOnClickListener {
            println("ADDED")
            lifecycleScope.launch(Dispatchers.IO) {
                val pers = PersItem(
                    name = binding.hNick.text.toString(),
                    status = binding.hStatus.text.toString(),
                    species = binding.hSpacies.text.toString(),
                    gender = binding.hGender.text.toString(),
                    location = binding.hLocation.text.toString(),
                    origin = binding.hOrigin.text.toString(),
                    type = binding.hType.text.toString(),
                    image =  binding.hNick.text.toString().replace(" ", "_"),
                    id = intent.getIntExtra("id", 0),
                    location_dim = binding.hLocationDic.text.toString(),
                    location_type = binding.hLocationType.text.toString(),
                    image_url = intent.getStringExtra("img_url") ?: ""
                )

//                println(db.personDao().getOnePerson(binding.hNick.text.toString()))
                if (db.personDao().getOnePerson(binding.hNick.text.toString()).toString() == "[]") {
                    db.personDao().addPerson(pers)
                    binding.hFavBtn.setImageResource(R.drawable.star_on)
                    savePhoto(pers.image.toString(), binding.hImg.drawToBitmap())
                }
                else {
                    db.personDao().deletePerson(binding.hNick.text.toString())
                    binding.hFavBtn.setImageResource(R.drawable.star_off)
                    deletePhoto(pers.image.toString())
                }
            }
        }

    }
    private fun init(presList: List<String>){
        recyclerView = findViewById(R.id.pers_recyc)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)

        var persList = arrayListOf<String>()

        presList.forEach{
            persList.add(it.split("/").last())
        }

        persAdapter = PersAdapter(persList)
        recyclerView.adapter = persAdapter
    }

    fun getOldItem(): Int {
        val id = intent.getIntExtra("id", -1)
        return id
    }

    private fun savePhoto(filename: String, bmp: Bitmap){
        try {
            openFileOutput("$filename.jpg", MODE_PRIVATE).use { stream ->
                if (!bmp.compress(Bitmap.CompressFormat.JPEG, 95, stream)){
                    throw IOException("Couldn`t save")
                }
            }
        } catch (e: IOException){
            e.printStackTrace()
        }
    }
    private suspend fun loadPhoto(filename: String) : Bitmap? {
        return withContext(Dispatchers.IO) {
//            try {
                val options = BitmapFactory.Options()
                options.inPreferredConfig = Bitmap.Config.ARGB_8888
                val filepath = filesDir.path + "/$filename.jpg"
                return@withContext BitmapFactory.decodeFile(filepath, options)
//            } catch (e: IOException) {
//                e.printStackTrace()
//                return@withContext
//            }
        }
    }
    private fun deletePhoto(filename: String){
        try {
            deleteFile("$filename.jpg")
        } catch (e: IOException){
            e.printStackTrace()
        }
    }

}