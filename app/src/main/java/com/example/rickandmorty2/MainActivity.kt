package com.example.rickandmorty2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty2.RNM.PersonsList

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyckerViewAdapter: RecyckerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recycleView)
        initRecycleView()
        initViewModel()
    }

    private fun initRecycleView() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            val decoration = DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL)
            addItemDecoration(decoration)
            recyckerViewAdapter = RecyckerViewAdapter()
            adapter = recyckerViewAdapter

        }
    }

    private fun initViewModel(){
        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getRecyclerListObserver()?.observe(this, Observer<PagedList<PersonsList>> {
            if (it != null){
                recyckerViewAdapter.submitList(it)
            } else {
                Toast.makeText(this@MainActivity, "Ошибка получения данных", Toast.LENGTH_LONG).show()
            }
        })
    }
}