package com.example.rickandmorty2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagedList
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty2.RNM.PersonsList
import com.example.rickandmorty2.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var recyckerViewAdapter: RecyckerViewAdapter
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recyclerView = findViewById(R.id.recycleView)
        initRecycleView()
        initViewModel()
    }

    private fun initRecycleView() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            val decoration = DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL)
            addItemDecoration(decoration)
            recyckerViewAdapter = RecyckerViewAdapter(
                onClickItem = {
                    lifecycleScope.launch(Dispatchers.IO) {
                        val intent = Intent(this@MainActivity, ShowInfo::class.java)
                        intent.putExtra("id", it?.id)
                        startActivity(intent)
                    }

                }
            )
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