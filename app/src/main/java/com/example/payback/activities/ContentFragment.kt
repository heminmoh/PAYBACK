
/**
 * the major activity for show data by using HitsContentsAdapter
 * has been bound with ActivityHitContentsBinding
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
import androidx.core.content.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
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


    private var checkConnectionInternetConnection: Boolean = false
    private var connectionInternetConnection= CheckInternetConnection()

    private lateinit var hitsRecycler : RecyclerView
    private lateinit var searchEditText : EditText
    private lateinit var imageSearch : ImageView

    private lateinit var progressDialog : PayBackProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hitsRecycler = binding.HitsRecycler

        imageSearch = view.findViewById<ImageView>(R.id.imgSearch)
        searchEditText = view.findViewById<EditText>(R.id.SearchEditText)
        searchEditText.setText(R.string.fruits)
        hitsRecycler.layoutManager = LinearLayoutManager(this.context)
        checkConnectionInternetConnection = this.context?.let {
            connectionInternetConnection.checkForInternet(
                it
            )
        } == true

        if(!checkConnectionInternetConnection) {
            Toast.makeText(this.context,"Check Network - Offline Mode", Toast.LENGTH_LONG).show()
        }
        hideKeyboard(searchEditText)
        initViewModel(searchEditText.text.toString())

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
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContentBinding.inflate(inflater, container, false)
        return binding.root
    }
    private fun initViewModel(SearchedText : String)
    {
        progressDialog = PayBackProgressDialog()
        this.context?.let { progressDialog.show(it,"Please Wait...") }

        val viewModel =  ViewModelProvider(this)[HitViewModel::class.java]
        this.context?.let {
            viewModel.getListObservable(it,SearchedText).observe(viewLifecycleOwner) {
                if (it != null) {
                    makeViewDesign(it)
                } else {
                    Toast.makeText(this.context, R.string.NoDataFetched, Toast.LENGTH_SHORT).show()
                }
                progressDialog.dialog.dismiss()
            }
        }
    }

    private fun makeViewDesign(hitsList : HitModel?)
    {
        val adapter = hitsList?.hits?.let { HitsContentsAdapter(it) }
        hitsRecycler.adapter = adapter

    }

    private fun hideKeyboard(view: View)
    {
        val manager = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        manager.hideSoftInputFromWindow(view.windowToken, 0)
    }



}