/**
 * HitsContentsAdapter
 * display the Hits items by this Adapter
 * Data bound to HitItemBinding by HitViewHolder
 * in section onBindViewHolder when user click on item an object send to another activity by intent
 * 2022-06-18 21:35
 */


package com.example.payback.adapter

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.payback.R
import com.example.payback.binding.HitViewHolder
import com.example.payback.databinding.HitItemBinding
import com.example.payback.models.Hits
import javax.inject.Inject


class HitsContentsAdapter  @Inject constructor (private val HitsContentList: List<Hits>, searched : String) :RecyclerView.Adapter<HitViewHolder>() {

    var context: Context? = null
    private lateinit var binding: HitItemBinding
     private lateinit var navController : NavController
     private var searchedData = searched
    @Inject
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):  HitViewHolder {

        binding = HitItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        context = parent.context
        navController = Navigation.findNavController(parent)
        return HitViewHolder(binding)
    }
    @Inject
    override fun getItemCount(): Int {

        return HitsContentList.size
    }

    @Inject
    override fun onBindViewHolder(holder: HitViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setIcon(R.drawable.paybacklogo)
            builder.setTitle("Are You Sure?")
            builder.setMessage("You want to see more details...")
            builder.setPositiveButton(R.string.yes) { _, _ ->
                val bundle = Bundle()
                bundle.putParcelable(R.string.`object`.toString(), HitsContentList[position])
                bundle.putString("Value", searchedData)
                navController.navigate(R.id.action_contentFragment_to_detailFragment,bundle)
            }

            builder.setNegativeButton(R.string.no) { dialog, _ -> dialog.dismiss() }


            builder.show()


        }
        return holder.bind(HitsContentList[position])
    }


}