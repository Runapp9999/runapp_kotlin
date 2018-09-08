package com.example.ben.startkotlin

import android.app.DownloadManager
import android.content.ContentValues
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.*
import kotlinx.android.synthetic.main.activity_main.*
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        login();
    }

    fun login(){
        // Instantiate the cache
        val cache = DiskBasedCache(cacheDir, 1024 * 1024) // 1MB cap

        // Set up the network to use HttpURLConnection as the HTTP client.
        val network = BasicNetwork(HurlStack())

        // Instantiate the RequestQueue with the cache and network. Start the queue.
        val requestQueue = RequestQueue(cache, network).apply {
            //start()
        }

        val account = findViewById<TextView>(R.id.appear)
        val userName = findViewById<EditText>(R.id.userName)
        val passWord = findViewById<EditText>(R.id.passWord)
        //click to connect server
        login.setOnClickListener {
            //login.text="you click it"
            val queue = Volley.newRequestQueue(this)
            val acc: String = "test"
            val url = "http://1.34.144.216:8080/runform.aspx?"
            val user = userName.getText().toString()
            val pass = passWord.getText().toString()
            val connect = "${url}"+"account="+"${user}"+"&password="+"${pass}"

            val stringRequest = StringRequest(Request.Method.GET, connect,
                    Response.Listener<String> { response ->
                        appear.text = "userID is: ${response}"
                    },
                    Response.ErrorListener { appear.text = "That didn't work!" })
            queue.add(stringRequest)
            // Add the request to the RequestQueue.
            requestQueue?.add(stringRequest)
        }
    }

}

