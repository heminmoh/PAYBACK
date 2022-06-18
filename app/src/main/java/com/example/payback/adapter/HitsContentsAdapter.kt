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
import com.example.payback.models.Hits
import com.squareup.picasso.Picasso
import com.example.payback.databinding.HitItemBinding
import com.example.payback.binding.HitViewHolder
import javax.inject.Inject


public class HitsContentsAdapter  @Inject constructor (private val HitsContentList: List<Hits>) :RecyclerView.Adapter<HitViewHolder>() {

    var context: Context? = null
    private lateinit var binding: HitItemBinding
    @Inject
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):  HitViewHolder {

        binding = HitItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        context = parent.context
        return HitViewHolder(binding)
    }
    @Inject
    override fun getItemCount(): Int {

        return HitsContentList.size
    }

    @Inject
    override fun onBindViewHolder(holder: HitViewHolder, position: Int) {
        holder.itemView.setOnClickListener(View.OnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setIcon(R.drawable.paybacklogo)
            builder.setTitle("Are You Sure?")
            builder.setMessage("You want to see more details...")
            builder.setPositiveButton(R.string.yes) { _, _ ->
                val intent = Intent(context, HitDetailActivity::class.java)
                    intent.putExtra("ImageBiggerVersion", HitsContentList[position].largeImageURL)
                    intent.putExtra("UserName", HitsContentList[position].user)
                    intent.putExtra("Tags", HitsContentList[position].tags)
                    intent.putExtra("NumberOfLikes", HitsContentList[position].likes).toString()
                    intent.putExtra("NumberOfDownloads", HitsContentList[position].downloads).toString()
                    intent.putExtra("NumberOfComments", HitsContentList[position].comments).toString()
                    context?.startActivity(intent)
            }

            builder.setNegativeButton(R.string.no) { dialog, _ -> dialog.dismiss()   }


            builder.show()



        })
        return holder.bind(HitsContentList[position])
    }
    @Inject
    @BindingAdapter("imageUrl")
    fun loadImage(view: View,
                  imageUrl: String?) {
        val image: ImageView = view as ImageView
        Picasso.get().load(imageUrl).into(image)
    }


}