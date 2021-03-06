package fr.isen.androidtoolbox

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_form.*
import org.json.JSONObject
import java.io.File
import java.text.SimpleDateFormat
import java.time.DateTimeException
import java.time.Duration
import java.time.Duration.between
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import java.util.*
import java.time.LocalDate.of as of1

class FormActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        set.setOnClickListener() {


            val userNom = nom.text.toString()
            val userPrenom = prenom.text.toString()
            val userDate = birthDate.text.toString()
            val answer = JSONObject()

            answer.put("nom", userNom)
            answer.put("prenom", userPrenom)
            answer.put("date de naissance", userDate)

            val jsonString = answer.toString()
            File(cacheDir.absolutePath + "save.json").createNewFile()
            File(cacheDir.absolutePath + "save.json").writeText(jsonString)

        }
        val cal = Calendar.getInstance()
        val dateSetListener =
            DatePickerDialog.OnDateSetListener{ datePicker: DatePicker, year: Int, monthofYear: Int, dayOfMonth: Int ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthofYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                val formater = SimpleDateFormat("dd/MM/yyyy", Locale.FRENCH)
                birthDate.text = formater.format(cal.time)//"$dayOfMonth/$monthofYear/$year"
            }
        fun showDatePicker(dateSetListener: DatePickerDialog.OnDateSetListener, cal: Calendar) {
            DatePickerDialog(
                this@FormActivity,
                dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
        birthDate.setOnClickListener{
            showDatePicker(dateSetListener , cal)
        }


        get.setOnClickListener() {


            val result = File(cacheDir.absolutePath + "save.json").readText(Charsets.UTF_8)
            val json = JSONObject(result)
            val alertDialogBuilder = AlertDialog.Builder(this)
            alertDialogBuilder.setTitle("profil utilisateur : ")
            alertDialogBuilder.setMessage(
                "Nom :" + json.get("nom").toString() + "\n" + "Prénom :" + json.get(
                    "prenom"
                ).toString() + "\n" + "Date de Naissance :" + json.get("date de naissance").toString() + "\n"
            )
            alertDialogBuilder.show()


            val birth = json.get("date de naissance").toString()
           // var date =(birth.substring(1, 2).toInt(); birth.substring(4, 5).toInt() ; birth.substring(7, 10).toInt())
            val cal = Calendar.getInstance()

            var age = cal.get(Calendar.YEAR) - birth.substringAfterLast("/").toInt();
            if(cal.get(Calendar.MONTH) < birth.substringAfter("/").substringBefore("/").toInt() ){
                age=age - 1
            }
            if(cal.get(Calendar.MONTH) == birth.substringAfter("/").substringBefore("/").toInt() && cal.get(Calendar.DAY_OF_MONTH) < birth.substringBefore("/").toInt() ){
                age= age - 1
            }

            alertDialogBuilder.setMessage(
                "Nom :" + json.get("nom").toString() + "\n" + "Prénom :" + json.get(
                    "prenom"
                ).toString() + "\n" + "Date de Naissance :" + json.get("date de naissance").toString() + "\n" + "age : " + age.toString()
            )
            alertDialogBuilder.show()
            if( age >= 18){
                alertDialogBuilder.setMessage(
                    "vous avez plus de 18 ans !\n" + json.get("prenom").toString() + " " +  json.get(
                        "nom"
                    ).toString() + "Venez découvrir lesquels de vos voisins sont les plus coquins !"
                )
                alertDialogBuilder.show()
            }


        }
    }
}
