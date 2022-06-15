package com.example.payback.activities

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.example.payback.R
import com.squareup.picasso.Picasso

class HitDetailActivity : AppCompatActivity() {

    lateinit var ImageViewFullSize : ImageView
    lateinit var UsernameTextView : TextView
    lateinit var TagsTextView : TextView
    lateinit var LikesTextView : TextView
    lateinit var DownloadsTextView : TextView
    lateinit var CommentsTextView : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hit_detail)
        ImageViewFullSize = (findViewById(R.id.ImageViewFullSize))
        UsernameTextView = (findViewById(R.id.UserNameDetailTextView))
        TagsTextView = (findViewById(R.id.TagsDetailTextView))
        LikesTextView = (findViewById(R.id.LikesDetailTextView))
        DownloadsTextView = (findViewById(R.id.DownloadsDetailTextView))
        CommentsTextView = (findViewById(R.id.CommentsDetailTextView))

        val ImageBiggerVersion :String = intent.getStringExtra("ImageBiggerVersion").toString()
        val UserName :String = intent.getStringExtra("UserName").toString()
        val Tags :String = intent.getStringExtra("Tags").toString()
        val NumberOfLikes :String = intent.getStringExtra("NumberOfLikes").toString()
        val NumberOfDownloads :String = intent.getStringExtra("NumberOfDownloads").toString()
        val NumberOfComments :String = intent.getStringExtra("NumberOfComments").toString()

        Picasso.get().load(ImageBiggerVersion).into(ImageViewFullSize)
        UsernameTextView.text = "UserName: $UserName"
        TagsTextView.text =  "Tags: $Tags"
        LikesTextView.text = "Likes: $NumberOfLikes"
        DownloadsTextView.text = "Downloads: $NumberOfDownloads"
        CommentsTextView.text = "Comments: $NumberOfComments"

    }
}