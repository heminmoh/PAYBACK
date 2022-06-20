/**
 * HitViewHolder
 * binding data with hitItem.xml
 * HitViewHolder take an object as HitItemBinding(hitItem) and by bind function, data bound.
 * 2022-06-19 02:55
 */

package com.example.payback.binding

import androidx.recyclerview.widget.RecyclerView
import com.example.payback.models.Hits
import com.example.payback.databinding.HitItemBinding
import javax.inject.Inject

class HitViewHolder  @Inject constructor (private val binding: HitItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(HitsData: Hits) {
        binding.metadata = HitsData
    }
}