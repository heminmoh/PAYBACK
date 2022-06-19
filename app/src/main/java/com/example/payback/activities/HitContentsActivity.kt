package com.example.payback.activities

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.payback.R
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.payback.adapter.HitsContentsAdapter
import com.example.payback.utilities.CheckInternetConnection
import com.example.payback.utilities.PayBackProgressDialog
import com.example.payback.viewmodels.HitViewModel
import com.example.payback.databinding.ActivityHitContentsBinding
import com.example.payback.models.HitModel


class HitContentsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHitContentsBinding

    private lateinit var checkConnectionInternetConnection: CheckInternetConnection

    private lateinit var hitsRecycler : RecyclerView
    private lateinit var searchEditText : EditText
    private lateinit var imageSearch : ImageView

    private lateinit var progressDialog : PayBackProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_hit_contents)

        hitsRecycler = binding.HitsRecycler

        imageSearch = findViewById (R.id.imgSearch)
        searchEditText = findViewById (R.id.SearchEditText)
        searchEditText.setText(R.string.fruits)
        hitsRecycler.layoutManager = LinearLayoutManager(this)
        checkConnectionInternetConnection = CheckInternetConnection()

        if(!checkConnectionInternetConnection.checkForInternet(this@HitContentsActivity)) {
           Toast.makeText(this,"Check Network - Offline Mode",Toast.LENGTH_LONG).show()

        }

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



    private fun initViewModel(SearchedText : String)
    {
        progressDialog = PayBackProgressDialog()
        progressDialog.show(this,"Please Wait...")



        val viewModel =  ViewModelProvider(this)[HitViewModel::class.java]
        viewModel.getListObservable(SearchedText).observe(this) {
            if (it != null) {
                makeViewDesign(it)
            } else {
                Toast.makeText(this, R.string.NoDataFetched, Toast.LENGTH_SHORT).show()
            }
            progressDialog.dialog.dismiss()

        }


        viewModel.makeApiCall(this@HitContentsActivity)



    }

    private fun makeViewDesign(hitsList : HitModel?)
    {
        val adapter = hitsList?.hits?.let { HitsContentsAdapter(it) }
        hitsRecycler.adapter = adapter

    }
}