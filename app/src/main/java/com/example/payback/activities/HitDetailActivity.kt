package com.example.payback.activities

import android.content.Context
import android.content.Intent
import android.icu.number.IntegerWidth
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.payback.R
import com.example.payback.adapter.DetailHitItemAdapter
import com.example.payback.adapter.HitsContentsAdapter
import com.example.payback.databinding.ActivityHitDetailBinding
import com.example.payback.models.Hits
import com.example.payback.models.hitmodel
import com.squareup.picasso.Picasso


const val EXTRA_IMAGE_URL = "ImageBiggerVersion"
const val EXTRA_UserName = "UserName"
const val EXTRA_Tags = "Tags"
const val EXTRA_NumberOfLikes = "NumberOfLikes"
const val EXTRA_NumberOfDownloads = "NumberOfDownloads"
const val EXTRA_NumberOfComments = "NumberOfComments"

class HitDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHitDetailBinding

    lateinit var DetailRecycler : RecyclerView
    lateinit var ImageViewFullSize : ImageView
    lateinit var UsernameTextView : TextView
    lateinit var TagsTextView : TextView
    lateinit var LikesTextView : TextView
    lateinit var DownloadsTextView : TextView
    lateinit var CommentsTextView : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_hit_detail)

        DetailRecycler = binding.DetailRecycler
        DetailRecycler.layoutManager = LinearLayoutManager(this)
//        ImageViewFullSize = (findViewById(R.id.ImageViewFullSize))


        val imageBiggerVersion = intent.getStringExtra(EXTRA_IMAGE_URL).toString()
        val userName           = intent.getStringExtra(EXTRA_UserName).toString()
        val tags               = intent.getStringExtra(EXTRA_Tags).toString()
        val numberOfLikes      = intent.getIntExtra(EXTRA_NumberOfLikes,0)
        val numberOfDownloads  = intent.getIntExtra(EXTRA_NumberOfDownloads,0)
        val numberOfComments   = intent.getIntExtra(EXTRA_NumberOfComments,0)
        var hit : Hits = Hits("","","",tags,"",0,0,""
        ,"","",imageBiggerVersion,0,0,0,0,numberOfDownloads
            ,0,numberOfLikes,numberOfComments,0,userName,"")
        init(hit)
//        Picasso.get().load(imageBiggerVersion).into(ImageViewFullSize)

    }
    private fun init(detail : Hits)
    {
        val adapter = DetailHitItemAdapter(detail)
        DetailRecycler.adapter = adapter
    }
}