/**
 * DetailHitItemAdapter
 * display the Hit item by this Adapter
 * Data bound to DetailItemBinding by DetailHitViewHolder
 * 2022-06-18 23:20
 */

package com.example.payback.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.payback.binding.DetailHitViewHolder
import com.example.payback.models.Hits
import com.example.payback.databinding.DetailItemBinding
import javax.inject.Inject


 class DetailHitItemAdapter  @Inject constructor (private val hitDetail: Hits) :RecyclerView.Adapter<DetailHitViewHolder>() {

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


}