package com.example.payback.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.payback.R
import com.example.payback.adapter.HitsContentsAdapter
import com.example.payback.models.hitmodel
import com.example.payback.models.hits
import com.example.payback.utilities.CheckInternetConnection
import com.example.payback.viewmodels.HitViewModel
import com.example.payback.utilities.PayBackProgressDialog

class HitContentsActivity : AppCompatActivity() {
    lateinit var checkconnectionInternetConnection: CheckInternetConnection

    lateinit var HitsRecycler : RecyclerView
    private val CheckNetwork : String ="Check Network"
    private val NoDataFetched : String ="No Data Fetched ..."
    lateinit var progressDialog : PayBackProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hit_contents)

        HitsRecycler = findViewById (R.id.HitsRecycler)
        HitsRecycler.layoutManager = LinearLayoutManager(this)
//        HitsRecycler.layoutManager = GridLayoutManager(this,2)


        checkconnectionInternetConnection = CheckInternetConnection()

        if(checkconnectionInternetConnection.checkForInternet(this@HitContentsActivity))
        {
            progressDialog = PayBackProgressDialog()
            progressDialog.show(this,"Please Wait...")
            initViewModel()
        }
        else
        {
            Toast.makeText(this@HitContentsActivity,CheckNetwork, Toast.LENGTH_SHORT).show()
        }

//        HitsRecycler.oncl(View.)

    }
    fun initViewModel()
    {
        val viewmodel = ViewModelProvider(this).get(HitViewModel::class.java)
        viewmodel.GetListOservable("id").observe(this, Observer<hitmodel> {
            if (it != null)
            {
                MakeViewDesign(it)
                progressDialog.dialog.dismiss()
            }
            else{
                Toast.makeText(this, NoDataFetched, Toast.LENGTH_SHORT).show()
            }
        })
        viewmodel.MakeApiCall()
    }

    private fun MakeViewDesign(hitsList : hitmodel?)
    {
        val adapter = hitsList?.hits?.let { HitsContentsAdapter(it) }
        HitsRecycler.adapter = adapter
    }
}