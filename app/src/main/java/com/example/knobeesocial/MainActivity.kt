package com.example.knobeesocial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.knobeesocial.adapter.FeedAdapter
import com.example.knobeesocial.viewmodel.DataViewModel
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {

    lateinit var dataViewModel: DataViewModel
    lateinit var rvItems: RecyclerView
    private var dataList: MutableList<List<Any>>? = ArrayList()
    lateinit var feedAdapter: FeedAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.statusBarColor = ContextCompat.getColor(this, R.color.white)

        dataViewModel = ViewModelProvider(this).get(DataViewModel::class.java)

        dataViewModel.loadData()

        dataViewModel.imagesLiveData.observe(this, Observer { t ->
            Log.d("TAG", "Live imagesLiveData "+Gson().toJson(t))
            if(dataList!!.isNotEmpty()){
                dataList!!.clear()
            }
            dataList?.addAll(t.getData()!!.gallery!!)
            feedAdapter.notifyDataSetChanged()
        })

        feedAdapter = FeedAdapter(this, dataList!!)

        rvItems = findViewById(R.id.rvItems)

        rvItems.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rvItems.adapter = feedAdapter

    }
}