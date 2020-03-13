package fr.isen.androidtoolbox

import android.Manifest
import android.content.ContentValues.TAG
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_contact.*
import java.security.AccessController.getContext

class ContactActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)
        ContactButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, REQUEST_CODE_GALLERY)
        }


         contactRecycler.adapter = ContactAdapter(listOf<String>("Juliette,Melvin"))
        setupPermissions();


    }
    override fun onActivityResult(requestCode: Int,resultCode: Int,data: Intent?){
        super.onActivityResult(requestCode,resultCode,data)
        data?.data.let {

            ContactButton.setImageURI(it)
        }
    }

    companion object {
        private const val REQUEST_CODE_GALLERY = 22
        private const val REQUEST_CODE_CONTACT =25

    }
}

private fun setupPermissions() {
  //  val permission = ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)

   /* if (permission != PackageManager.PERMISSION_GRANTED) {
        Log.i(TAG, "Permission to record denied")
        makeRequest()
    }*/
}

private fun makeRequest() {
   // ActivityCompat.requestPermissions(this,
  //      arrayOf(Manifest.permission.RECORD_AUDIO),
    //    )
}
/*
fun onRequestPermissionsResult(requestCode: Int,
                                         permissions: Array<String>, grantResults: IntArray) {
    when (requestCode) {
        REQUEST_CODE_CONTACT -> {

            if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {

                Log.i(TAG, "Permission has been denied by user")
            } else {
                Log.i(TAG, "Permission has been granted by user")
            }
        }
    }
}﻿*/