package com.example.payback.activities

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
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


class HitContentsActivity : AppCompatActivity() {
    lateinit var checkconnectionInternetConnection: CheckInternetConnection

    lateinit var HitsRecycler : RecyclerView
    lateinit var SearchEditText : EditText
    lateinit var ImageSearch : ImageView
    private val CheckNetwork : String ="Check Network"
    private val ProgressBarDialog : String ="Please Wait..."
    private val NoDataFetched : String ="No Data Fetched ..."
    lateinit var progressDialog : PayBackProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hit_contents)

        HitsRecycler = findViewById (R.id.HitsRecycler)
        ImageSearch = findViewById (R.id.imgSearch)
        SearchEditText = findViewById (R.id.SearchEditText)
        SearchEditText.setText("fruits")

        HitsRecycler.layoutManager = LinearLayoutManager(this)
//        HitsRecycler.layoutManager = GridLayoutManager(this,2)


        checkconnectionInternetConnection = CheckInternetConnection()

        if(checkconnectionInternetConnection.checkForInternet(this@HitContentsActivity))
        {

            initViewModel(SearchEditText.text.toString())
        }
        else
        {
            Toast.makeText(this@HitContentsActivity,CheckNetwork, Toast.LENGTH_SHORT).show()
        }

        ImageSearch.setOnClickListener(View.OnClickListener {
            initViewModel(SearchEditText.text.toString())
        })

    }



    fun initViewModel(SearchedText : String)
    {
        progressDialog = PayBackProgressDialog()
        progressDialog.show(this,ProgressBarDialog)
        val viewmodel = ViewModelProvider(this)[HitViewModel::class.java]
        viewmodel.GetListOservable(SearchedText).observe(this, Observer<hitmodel> {
            if (it != null)
            {
                MakeViewDesign(it)
            }
            else{
                Toast.makeText(this, NoDataFetched, Toast.LENGTH_SHORT).show()

            }
            progressDialog.dialog.dismiss()
        })
        viewmodel.MakeApiCall()
    }

    private fun MakeViewDesign(hitsList : hitmodel?)
    {
        val adapter = hitsList?.hits?.let { HitsContentsAdapter(it) }
        HitsRecycler.adapter = adapter
    }
}