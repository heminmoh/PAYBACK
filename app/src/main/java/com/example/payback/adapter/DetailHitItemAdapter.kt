package com.example.payback.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.payback.R
import com.example.payback.activities.HitDetailActivity
import com.example.payback.binding.DetailHitViewHolder
import com.example.payback.models.Hits
import com.squareup.picasso.Picasso
import com.example.payback.databinding.DetailItemBinding
import javax.inject.Inject


public class DetailHitItemAdapter  @Inject constructor (private val hitDetail: Hits) :RecyclerView.Adapter<DetailHitViewHolder>() {

    var context: Context? = null
    private lateinit var binding: DetailItemBinding
    @Inject
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailHitViewHolder {

        binding = DetailItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        context = parent.context
        return DetailHitViewHolder(binding)
    }
    @Inject
    override fun getItemCount(): Int {

        return 1
    }

    @Inject
    override fun onBindViewHolder(holder: DetailHitViewHolder, position: Int) {
        return holder.bind(hitDetail)

    }
    @Inject
    @BindingAdapter("imageUrl")
    fun loadImage(view: View,
                  imageUrl: String?) {
        val image: ImageView = view as ImageView
        Picasso.get().load(imageUrl).placeholder(R.drawable.loading).into(image)
    }


}