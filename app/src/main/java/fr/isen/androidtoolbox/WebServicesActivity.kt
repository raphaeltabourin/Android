package fr.isen.androidtoolbox


import WebServicesActivityAdapter
import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_web_services.*
import kotlinx.android.synthetic.main.activity_web_services_cell.*

class WebServicesActivity : AppCompatActivity() {


    private val url = "https://randomuser.me/api/?inc=name,location,picture,email"
    internal lateinit var adapter: WebServicesActivityAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        makeRequest()

        setContentView(R.layout.activity_web_services)



            setContentView(R.layout.activity_web_services)
            val url = "https://randomuser.me/api/?results=10&nat=fr"
            WebServiceTask(object : WebServicesInterface {
                override fun onSuccess(result: String) {
                    var gson = Gson()
                    var model : Character = gson.fromJson(result, Character::class.java)
                    adapter = WebServicesActivityAdapter(this@WebServicesActivity, model.results)

                    recyclerViewWebServices.layoutManager = LinearLayoutManager(this@WebServicesActivity)
                    recyclerViewWebServices.itemAnimator = DefaultItemAnimator()


                    recyclerViewWebServices.adapter = adapter
                }


                override fun onError() {
                    Log.d("Error", "WebServiceTask failed..")
                }

            }).execute(url)

        }
    private fun makeRequest() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.INTERNET), 1
        )
    }}



