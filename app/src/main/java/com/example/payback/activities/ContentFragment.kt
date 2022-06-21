
/**
 * the major fragment for show data by using HitsContentsAdapter
 * has been bound with FragmentContentBinding
 *  If access denied to data ?
 *  it use the cache data in Okhttp Process
 *  initViewModel function take a string and call api and make view by recycleView
 *  hideSoftKeyboard dismiss keyboard when activity
 * 2022-06-18 13:00
 */
package com.example.payback.activities

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.payback.R
import com.example.payback.adapter.HitsContentsAdapter
import com.example.payback.databinding.FragmentContentBinding
import com.example.payback.models.HitModel
import com.example.payback.utilities.CheckInternetConnection
import com.example.payback.utilities.PayBackProgressDialog
import com.example.payback.viewmodels.HitViewModel


class ContentFragment : Fragment() {
    private lateinit var binding: FragmentContentBinding
    private lateinit var navController : NavController

    private var checkConnectionInternetConnection: Boolean = false
    private var connectionInternetConnection= CheckInternetConnection()

    private lateinit var hitsRecycler : RecyclerView
    private lateinit var searchEditText : EditText
    private lateinit var imageSearch : ImageView

    private lateinit var progressDialog : PayBackProgressDialog

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hitsRecycler = binding.HitsRecycler
        navController = Navigation.findNavController(view)
        imageSearch = view.findViewById(R.id.imgSearch)
        searchEditText = view.findViewById(R.id.SearchEditText)

        hitsRecycler.layoutManager = LinearLayoutManager(this.context)
        checkConnectionInternetConnection = this.context?.let {
            connectionInternetConnection.checkForInternet(
                it
            )
        } == true

        if(!checkConnectionInternetConnection) {
            Toast.makeText(this.context,"Check Network - Offline Mode", Toast.LENGTH_LONG).show()
        }
      val value : String? =  this.arguments?.getString("Value")

            searchEditText.setText(value)

        initViewModel(searchEditText.text.toString())
        hideKeyboard(searchEditText)

        imageSearch.setOnClickListener {
            val searchedDataText = searchEditText.text.toString()
            initViewModel(searchedDataText)
        }
        searchEditText.setOnEditorActionListener { _, actionId, _ ->
            if(actionId == EditorInfo.IME_ACTION_SEARCH){
                val searchedDataText = searchEditText.text.toString()
                initViewModel(searchedDataText)
                true
            } else {
                false
            }
        }

        requireActivity().onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    navController.navigate(R.id.action_contentFragment_to_mainFragment)
                }
            })
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
            binding = FragmentContentBinding.inflate(inflater, container, false)

        return binding.root
    }
    private fun initViewModel(SearchedText : String)
    {
        progressDialog = PayBackProgressDialog()
        this.context?.let { progressDialog.show(it,"Please Wait...") }

        val viewModel =  ViewModelProvider(this)[HitViewModel::class.java]
        this.context?.let { it ->
            viewModel.getListObservable(it,SearchedText).observe(viewLifecycleOwner) {
                if (it != null) {
                    makeViewDesign(it,SearchedText)
                } else {
                    Toast.makeText(this.context, R.string.NoDataFetched, Toast.LENGTH_SHORT).show()
                }
                progressDialog.dialog.dismiss()
            }
        }
    }

    private fun makeViewDesign(hitsList : HitModel?, searched : String)
    {
        val adapter = hitsList?.hits?.let { HitsContentsAdapter(it,searched) }
        hitsRecycler.adapter = adapter

    }

    private fun hideKeyboard(view: View)
    {
        val manager = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        manager.hideSoftInputFromWindow(view.windowToken, 0)
    }



}