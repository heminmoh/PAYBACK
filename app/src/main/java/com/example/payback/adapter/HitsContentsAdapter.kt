package com.example.payback.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.payback.R
import com.example.payback.activities.HitContentsActivity
import com.example.payback.activities.HitDetailActivity
import com.example.payback.models.hitmodel
import com.example.payback.models.hits
import com.squareup.picasso.Picasso
import kotlinx.serialization.Serializable

class HitsContentsAdapter (private val HitsContentList: List<hits>) :RecyclerView.Adapter<HitsContentsAdapter.ViewHolder>() {

    var context: Context? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view  = LayoutInflater.from(parent.context).inflate(R.layout.hit_item,parent,false)
        context = parent.context
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {

        return HitsContentList.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener(View.OnClickListener {
            val intent = Intent(context, HitDetailActivity::class.java)
            intent.putExtra("ImageBiggerVersion", HitsContentList[position].largeImageURL)
            intent.putExtra("UserName", HitsContentList[position].user)
            intent.putExtra("Tags", HitsContentList[position].tags)
            intent.putExtra("NumberOfLikes", HitsContentList[position].likes.toString())
            intent.putExtra("NumberOfDownloads", HitsContentList[position].downloads.toString())
            intent.putExtra("NumberOfComments", HitsContentList[position].comments.toString())
            context?.startActivity(intent)
        })
        return holder.bind(HitsContentList[position])
    }
    class ViewHolder(itemView : View) :RecyclerView.ViewHolder(itemView) {


        var HitImageView     = itemView.findViewById<ImageView>(R.id.HitItemImage)
        var UserNameTextView = itemView.findViewById<TextView>(R.id.UserNameTextView)
        var TagTextView      = itemView.findViewById<TextView>(R.id.TagTextView)





        fun bind(Hititems: hits) {

            val Username : String? = Hititems.user
            val Tags : String? = Hititems.tags


            UserNameTextView.text = "UserName: $Username"
            TagTextView.text = "Tags: $Tags"
            Picasso.get().load(Hititems.previewURL).into(HitImageView)
        }

    }
}