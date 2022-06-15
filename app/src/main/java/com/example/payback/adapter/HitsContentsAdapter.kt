package com.example.payback.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.payback.R
import com.example.payback.models.hitmodel
import com.example.payback.models.hits
import com.squareup.picasso.Picasso

class HitsContentsAdapter (private val HitsContentList: List<hits>) :RecyclerView.Adapter<HitsContentsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view  = LayoutInflater.from(parent.context).inflate(R.layout.hit_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {

        return HitsContentList.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(HitsContentList[position])
    }
    class ViewHolder(itemView : View) :RecyclerView.ViewHolder(itemView) {


        var HitImageView     = itemView.findViewById<ImageView>(R.id.HitItemImage)
        var UserNameTextView = itemView.findViewById<TextView>(R.id.UserNameTextView)
        var TagTextView      = itemView.findViewById<TextView>(R.id.TagTextView)


        


        fun bind(Hititems: hits) {

            UserNameTextView.text = Hititems.user
            TagTextView.text = Hititems.tags
            Picasso.get().load(Hititems.previewURL).into(HitImageView)
        }

    }
}