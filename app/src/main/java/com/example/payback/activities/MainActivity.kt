
/**
 * the Main activity for show data first page
 * By liveDataConnection check the network and show proportional result
 *
 * 2022-06-16 - 1630
 */

package com.example.payback.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.payback.R
import com.example.payback.utilities.LiveDataInternetConnections
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

//    @Inject
//    lateinit var liveDataConnection : LiveDataInternetConnections
//    private lateinit var textviewConnect : TextView
//    private lateinit var textViewDisconnect : TextView
//    private lateinit var buttonIntent : Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




    }
}