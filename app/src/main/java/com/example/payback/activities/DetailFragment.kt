/**
 * the detail fragment for show data in detail  by using DetailHitItemAdapter
 * has been bound with FragmentDetailBinding
 *  If access denied to data ?
 *  it use the cache data in Okhttp Process
 *  init function take an Hits object  and call make view by recycleView
 * 2022-06-19 09:00
 */

package com.example.payback.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.payback.R
import com.example.payback.adapter.DetailHitItemAdapter
import com.example.payback.databinding.FragmentDetailBinding
import com.example.payback.models.Hits

class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private lateinit var navController : NavController
    private lateinit var detailRecycler : RecyclerView

    val detailFragment = "DetailFragment"
    val resource = "Resource"
    val value = "Value"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detailRecycler = binding.DetailRecycler
        detailRecycler.layoutManager = LinearLayoutManager(this.context)
        navController = Navigation.findNavController(view)
        val hitObject: Hits? = this.arguments?.getParcelable(R.string.`object`.toString())
        val valueReceived: String? = this.arguments?.getString("Value")
        if (hitObject != null) {
            init(hitObject)
        }

        requireActivity().onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    val bundle = Bundle()
                    bundle.putString(resource, detailFragment)
                    bundle.putString(value, valueReceived)
                    navController.navigate(R.id.action_detailFragment_to_contentFragment3,bundle)
                }
            })
    }
    private fun init(detail : Hits)
    {

        val adapter = DetailHitItemAdapter(detail)
        detailRecycler.adapter = adapter
    }



}