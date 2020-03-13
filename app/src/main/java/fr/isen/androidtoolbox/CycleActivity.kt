package fr.isen.androidtoolbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_cycle.*

class CycleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cycle)
        cycle_text.text = "activity created"
    }
    override fun onPause() {
        super.onPause()
       // Log.i("pause","app paused")
        cycle_text.text=cycle_text.text.toString()+"\nactivity Paused"
    }
    override fun onResume() {
        super.onResume()
       cycle_text.text=cycle_text.text.toString()+"\nactivity Resumed"
    }
    override fun onStop() {
        super.onStop()
        Log.i("stoped","activity Stoped ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, "activity destroyed", Toast.LENGTH_SHORT).show()
        Log.i("destoy ","activity Destroyed ")
    }

    }
