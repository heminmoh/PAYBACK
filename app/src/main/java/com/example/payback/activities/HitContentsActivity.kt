package com.example.payback.activities

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.graphics.Color
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.payback.R
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.payback.adapter.HitsContentsAdapter
import com.example.payback.models.hitmodel
import com.example.payback.utilities.CheckInternetConnection
import com.example.payback.utilities.PayBackProgressDialog
import com.example.payback.viewmodels.HitViewModel
import java.security.AccessController.getContext
import com.example.payback.databinding.ActivityHitContentsBinding
import com.example.payback.remote.ServiceBuilder


class HitContentsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHitContentsBinding

    lateinit var checkconnectionInternetConnection: CheckInternetConnection

    lateinit var HitsRecycler : RecyclerView
    lateinit var SearchEditText : EditText
    lateinit var ImageSearch : ImageView

    lateinit var progressDialog : PayBackProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_hit_contents)

        HitsRecycler = binding.HitsRecycler

        ImageSearch = findViewById (R.id.imgSearch)
        SearchEditText = findViewById (R.id.SearchEditText)
        SearchEditText.setText("fruits")
        HitsRecycler.layoutManager = LinearLayoutManager(this)
        checkconnectionInternetConnection = CheckInternetConnection()

        if(!checkconnectionInternetConnection.checkForInternet(this@HitContentsActivity)) {
           Toast.makeText(this,"Check Network - Offline Mode",Toast.LENGTH_LONG).show()

        }

            initViewModel(SearchEditText.text.toString())

        ImageSearch.setOnClickListener(View.OnClickListener {
            var searchedDataText = SearchEditText.text.toString()
            initViewModel(searchedDataText)
        })

        SearchEditText.setOnEditorActionListener { _, actionId, _ ->
            if(actionId == EditorInfo.IME_ACTION_SEARCH){
                var searchedDataText = SearchEditText.text.toString()
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
        viewModel.getListObservable(SearchedText).observe(this, Observer<hitmodel> {
            if (it != null)
            {
                makeViewDesign(it)
            }
            else{
                Toast.makeText(this, R.string.NoDataFetched, Toast.LENGTH_SHORT).show()
            }
            progressDialog.dialog.dismiss()

        })


        viewModel.makeApiCall(this@HitContentsActivity)



    }

    private fun makeViewDesign(hitsList : hitmodel?)
    {
        val adapter = hitsList?.hits?.let { HitsContentsAdapter(it) }
        HitsRecycler.adapter = adapter

    }
}