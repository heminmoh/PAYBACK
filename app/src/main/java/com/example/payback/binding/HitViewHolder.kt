package com.example.payback.binding

import androidx.recyclerview.widget.RecyclerView
import com.example.payback.models.Hits
import com.example.payback.databinding.HitItemBinding
import javax.inject.Inject

class HitViewHolder  @Inject constructor (private val binding: HitItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(hitsdata: Hits) {
        binding.hitsdata = hitsdata
    }
}