package com.example.payback.binding

import androidx.recyclerview.widget.RecyclerView
import com.example.payback.activities.HitDetailActivity
import com.example.payback.databinding.DetailItemBinding
import com.example.payback.models.Hits
import com.example.payback.databinding.HitItemBinding
import javax.inject.Inject

class DetailHitViewHolder  @Inject constructor (private val binding: DetailItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(HitsData: Hits) {
        binding.metadata = HitsData
    }
}