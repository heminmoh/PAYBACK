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

    @Inject
    lateinit var liveDataConnection : LiveDataInternetConnections
    private lateinit var textviewConnect : TextView
    private lateinit var textViewDisconnect : TextView
    private lateinit var buttonIntent : Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonIntent = findViewById(R.id.intentcall)
        textviewConnect = findViewById(R.id.connected)
        textViewDisconnect = findViewById(R.id.not_connected)
        liveDataConnection = LiveDataInternetConnections(application)
        textviewConnect.visibility = View.GONE
        textViewDisconnect.visibility = View.VISIBLE



        liveDataConnection.observe(this) { isConnected ->
            if (isConnected) {
                textviewConnect.visibility = View.GONE
                textViewDisconnect.visibility = View.GONE
            }else {
                textviewConnect.visibility = View.GONE
                textViewDisconnect.visibility = View.VISIBLE
            }
        }

        buttonIntent.setOnClickListener {

            intent = Intent(this, HitContentsActivity::class.java)
            startActivity(intent)
        }


    }
}