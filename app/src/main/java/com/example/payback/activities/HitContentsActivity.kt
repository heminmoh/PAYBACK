package com.example.payback.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

class HitContentsActivity : AppCompatActivity() {
    lateinit var checkconnectionInternetConnection: CheckInternetConnection

    lateinit var HitsRecycler : RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hit_contents)

        HitsRecycler = findViewById (R.id.HitsRecycler)
        HitsRecycler.layoutManager = LinearLayoutManager(this)
//        HitsRecycler.layoutManager = GridLayoutManager(this,2)


        checkconnectionInternetConnection = CheckInternetConnection()

        if(checkconnectionInternetConnection.checkForInternet(this@HitContentsActivity))
        {

            initViewModel()
        }
        else
        {
            Toast.makeText(this@HitContentsActivity,"Check Network", Toast.LENGTH_SHORT).show()
        }

    }
    fun initViewModel()
    {
        val viewmodel = ViewModelProvider(this).get(HitViewModel::class.java)
        viewmodel.GetListOservable("id").observe(this, Observer<hitmodel> {
            if (it != null)
            {
                MakeViewDesign(it)
//                progressDialog.dialog.dismiss()
            }
            else{
                Toast.makeText(this,"No Fetch Data...", Toast.LENGTH_SHORT).show()
            }
        })
        viewmodel.MakeApiCall()
    }

    fun MakeViewDesign(hitsList : hitmodel?)
    {
        val data = ArrayList<hits>()


        //Push attribute objects to a List of Attribute model
        for (i in 0 until hitsList?.hits?.size!!)
        {
            data.add(
                hits("","","", hitsList.hits[i].tags, hitsList.hits[i].previewURL,
                    0,0,"","","",
                    "",0,0,0,0,0,0,
                    0,0,0, hitsList.hits[i].user,""
                    ))
        }


        val adapter = HitsContentsAdapter(data)

        // Setting the Adapter with the recyclerview
        HitsRecycler.adapter = adapter
    }
}