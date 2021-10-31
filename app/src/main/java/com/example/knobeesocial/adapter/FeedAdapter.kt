package com.example.knobeesocial.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.knobeesocial.R
import com.example.knobeesocial.model.DataModel

class FeedAdapter (var context: Context, var dataList: List<List<Any>>) : RecyclerView.Adapter<FeedAdapter.FeedViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        val m = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return FeedViewHolder(m)
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        val adapter = ChildAdapter(holder.rvChild.context, dataList[position])
        if(dataList[position].size == 1){
            holder.rvChild.layoutManager = LinearLayoutManager(this.context)
        }
        else if(dataList[position].isEmpty()){
            holder.rvChild.visibility = View.GONE
        }
        else{
            holder.rvChild.layoutManager = GridLayoutManager(this.context, 2)
        }
        holder.rvChild.adapter = adapter
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class FeedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val rvChild: RecyclerView = itemView.findViewById(R.id.rvChild)
    }

}