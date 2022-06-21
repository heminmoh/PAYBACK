/**
 * the detail activity for show data in detail  by using DetailHitItemAdapter
 * has been bound with ActivityHitDetailBinding
 *  If access denied to data ?
 *  it use the cache data in Okhttp Process
 *  init function take an Hits object  and call make view by recycleView
 * 2022-06-19 09:00
 */

package com.example.payback.activities

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.payback.adapter.DetailHitItemAdapter
import com.example.payback.databinding.FragmentDetailBinding
import com.example.payback.models.Hits

class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding

    private lateinit var detailRecycler : RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailRecycler = binding.DetailRecycler
        detailRecycler.layoutManager = LinearLayoutManager(this.context)

        val hitObject : Hits? = this.arguments?.getParcelable("object")
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