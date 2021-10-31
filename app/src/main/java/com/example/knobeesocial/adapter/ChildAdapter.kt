package com.example.knobeesocial.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.knobeesocial.R
import com.example.knobeesocial.model.DataModel
import com.google.gson.Gson
import org.json.JSONObject




class ChildAdapter(context: Context, var imagesList: List<Any>) : RecyclerView.Adapter<ChildAdapter.ChildViewHolder>() {
    class ChildViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val ivChildImage: ImageView = itemView.findViewById(R.id.ivChildImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildViewHolder {
        val m = LayoutInflater.from(parent.context).inflate(R.layout.child_layout, parent, false)
        return ChildViewHolder(m)
    }

    override fun onBindViewHolder(holder: ChildViewHolder, position: Int) {
        val url = imagesList[position].toString().subSequence(imagesList[position].toString().indexOf("=")+1,imagesList[position].toString().lastIndex)
        Log.d("TAG", "onBindViewHolder: childAdapter $url")
        Glide.with(holder.ivChildImage.context).load(url).into(holder.ivChildImage)
    }

    override fun getItemCount(): Int {
        return imagesList.size
    }
}