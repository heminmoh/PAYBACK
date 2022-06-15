package com.example.payback.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.payback.R
import com.example.payback.utilities.CheckInternetConnection
import com.example.payback.utilities.LiveDataInternetConnections

class MainActivity : AppCompatActivity() {

    private lateinit var CheckConnectionInternetConnection: CheckInternetConnection
    private lateinit var LiveDataConnection : LiveDataInternetConnections
    private lateinit var textviewConnect : TextView
    private lateinit var textviewdisconnect : TextView
    private lateinit var Button_Intent : Button


    private val CheckNetwork : String ="Check Network"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Button_Intent = findViewById(R.id.intentcall)
        textviewConnect = findViewById(R.id.connected)
        textviewdisconnect = findViewById(R.id.not_connected)
        LiveDataConnection = LiveDataInternetConnections(application)
        textviewConnect.visibility = View.GONE
        textviewdisconnect.visibility = View.VISIBLE



        LiveDataConnection.observe(this) { isConnected ->
            if (isConnected) {
                textviewConnect.visibility = View.GONE
                textviewdisconnect.visibility = View.GONE
            }else {
                textviewConnect.visibility = View.GONE
                textviewdisconnect.visibility = View.VISIBLE
            }
        }
        CheckConnectionInternetConnection = CheckInternetConnection()

        Button_Intent.setOnClickListener(View.OnClickListener {

            if(CheckConnectionInternetConnection.checkForInternet(this@MainActivity))
            {
                intent = Intent(this, HitContentsActivity::class.java)
                startActivity(intent)
            }
            else
                Toast.makeText(this@MainActivity,CheckNetwork, Toast.LENGTH_SHORT).show()

        })


    }
}