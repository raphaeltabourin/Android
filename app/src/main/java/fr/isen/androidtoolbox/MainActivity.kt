package fr.isen.androidtoolbox

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import android.content.SharedPreferences


var sharedPreferences: SharedPreferences? = null
private val PREF_NAME = "authentification_File"
private var PRIVATE_MODE = 0
private val user = "login"
private val Pw = "password"
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPref: SharedPreferences = getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        if (sharedPref.getString(user,"")=="admin" && sharedPref.getString(Pw,"")=="123") {

            val intent = Intent(this, HomeActivity::class.java) //f
            startActivity(intent)
            finish()


        }
        else {
            setContentView(R.layout.activity_main)
            validate_button.setOnClickListener() {
                if (login.text.toString() == "admin" && password.text.toString() == "123"  ) {
                    Toast.makeText(this, "autentification réussie", Toast.LENGTH_SHORT).show()


                    val intent = Intent(this, HomeActivity::class.java) //f
                    startActivity(intent)
                    val editor = sharedPref.edit()
                  editor.putString(user, login.text.toString())
                    editor.apply()
                   editor.putString(Pw, password.text.toString())

                    editor.apply()

                } else
                    Toast.makeText(this, "autentification ratée", Toast.LENGTH_SHORT).show()

            }

        }
    }
}

