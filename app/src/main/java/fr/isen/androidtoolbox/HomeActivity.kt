package fr.isen.androidtoolbox

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_home.*

private val PREF_NAME = "authentification_File"
private var PRIVATE_MODE = 0
class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPref: SharedPreferences = getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        setContentView(R.layout.activity_home)
        cycleButton.setOnClickListener(){
            val intent = Intent(this, CycleActivity::class.java) //f
            startActivity(intent)

            }
        logout.setOnClickListener(){
            val intent = Intent(this, MainActivity::class.java) //f
            startActivity(intent)
           val editor=sharedPref.edit()
            editor.clear()
            editor.apply()


        }
        set.setOnClickListener(){
            val intent = Intent(this, FormActivity::class.java) //f
            startActivity(intent)
        }
        BLEButton.setOnClickListener(){
            val intent = Intent(this, BLEScanActivity::class.java) //f
            startActivity(intent)
        }
            permissionBoutton.setOnClickListener() {
                val intent = Intent(this, ContactActivity::class.java) //f
                startActivity(intent)
            }
        webServicesButton.setOnClickListener(){
                    val intent = Intent(this, WebServicesActivity::class.java) //f
                    startActivity(intent)

        }
    }
}
