package com.example.payback.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.payback.R
import com.example.payback.adapter.DetailHitItemAdapter
import com.example.payback.databinding.ActivityHitDetailBinding
import com.example.payback.models.Hits


class HitDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHitDetailBinding

    private lateinit var detailRecycler : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_hit_detail)

        detailRecycler = binding.DetailRecycler
        detailRecycler.layoutManager = LinearLayoutManager(this)

        val hitObject : Hits? = intent.getParcelableExtra("object")
        if (hitObject != null) {
            init(hitObject)
        }

    }
    private fun init(detail : Hits)
    {

        val adapter = DetailHitItemAdapter(detail)
        detailRecycler.adapter = adapter
    }
}