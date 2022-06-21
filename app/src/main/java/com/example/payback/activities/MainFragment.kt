
/**
 * the Main Fragment for show data first page
 * By liveDataConnection check the network and show proportional result
 *
 * 2022-06-16 - 1630
 */

package com.example.payback.activities
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.payback.R
import com.example.payback.utilities.LiveDataInternetConnections
import javax.inject.Inject


class MainFragment : Fragment(),View.OnClickListener {

    private lateinit var navController : NavController
    @Inject
    lateinit var liveDataConnection : LiveDataInternetConnections
    private lateinit var textviewConnect : TextView
    private lateinit var textViewDisconnect : TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        textviewConnect =   view.findViewById(R.id.connected)
        textViewDisconnect =  view.findViewById(R.id.not_connected)
        view.findViewById<Button>(R.id.intentcall).setOnClickListener(this)

        liveDataConnection = activity?.let { LiveDataInternetConnections(it.application) }!!
        textviewConnect.visibility = View.GONE
        textViewDisconnect.visibility = View.VISIBLE

        liveDataConnection.observe(viewLifecycleOwner) { isConnected ->
            if (isConnected) {
                textviewConnect.visibility = View.GONE
                textViewDisconnect.visibility = View.GONE
            }else {
                textviewConnect.visibility = View.GONE
                textViewDisconnect.visibility = View.VISIBLE
            }
        }
    }

    override fun onClick(v: View?) {
            when(v!!.id)
            {
                R.id.intentcall ->{
                    val bundle = Bundle()
                    bundle.putString("Resource", "MainFragment")
                    bundle.putString("Value", "fruits")
                    navController!!.navigate(R.id.action_mainFragment_to_contentFragment,bundle)
//                    navController.navigate(R.id.action_mainFragment_to_contentFragment)
                }

            }
    }



}