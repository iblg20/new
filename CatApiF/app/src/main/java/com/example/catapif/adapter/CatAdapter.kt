package com.example.catapif.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.catapif.R
import com.example.catapif.model.CatDataItem
import kotlinx.android.synthetic.main.raw_items.view.*

class CatAdapter(val catList: List<CatDataItem>, val context: Context) :
    RecyclerView.Adapter<CatAdapter.ViewHolder>() {

    private lateinit var myListener : onItemClickListener

    interface onItemClickListener{

        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        myListener = listener
    }

    class ViewHolder(itemView: View, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {
        var url: ImageView
        var id: TextView

        init {
            url = itemView.image_cat
            id = itemView.cat_id
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.raw_items, parent, false)
        return ViewHolder(itemView, myListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context)
            .load(catList[position].url)
            .override(200, 200)
            .error(R.drawable.ic_baseline_pets_24)
            .into(holder.url)
        holder.id.text = "Image ID : " + catList[position].id

//        I've tried the same with Picasso
/*        Picasso.get()
            .load(catList[position].url)
            .resize(150, 100)
            .centerCrop()
            .error(R.drawable.ic_baseline_pets_24)
            .into(holder.url)
        holder.id.text = "Image ID : " + catList[position].id*/
    }

    override fun getItemCount(): Int {
        return catList.size
    }

}