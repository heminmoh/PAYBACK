package com.example.payback.utilities

import androidx.recyclerview.widget.RecyclerView
import com.example.payback.models.Hits
import com.example.payback.databinding.HitItemBinding

class LargeNewsViewHolder(
    private val binding: HitItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(hitsdata: Hits) {
        binding.hitsdata = hitsdata
    }
}