package fr.isen.androidtoolbox

import android.Manifest
import android.app.AlertDialog
import android.content.ContentResolver
import android.content.ContentValues.TAG
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build

import android.os.Bundle
import android.provider.ContactsContract
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_contact.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager


class ContactActivity : AppCompatActivity() {
    val contactListe = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        //contactRecycler.adapter = ContactActivityAdapter(listOf<String>("Juliette","Melvin"))

        super.onCreate(savedInstanceState);


        setupPermissions();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(
                Manifest.permission.READ_CONTACTS


            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(
                arrayOf(Manifest.permission.READ_CONTACTS),
                1
            )
        } else {
            val resolver: ContentResolver = contentResolver;
            val cursor = resolver.query(
                ContactsContract.Contacts.CONTENT_URI,
                null,
                null,
                null,
                null
            )

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val name =
                        cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                    contactListe.add("Nom : $name")


                }
            } else {
                Toast.makeText(applicationContext, "No contacts available!", Toast.LENGTH_SHORT)
                    .show()
            }
            cursor?.close()





        }

        setContentView(R.layout.activity_contact)
        ContactButton.setOnClickListener {
            /*   val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, REQUEST_CODE_GALLERY)*/

          */
            val pictureDialog: AlertDialog.Builder = AlertDialog.Builder(this)
            pictureDialog.setTitle("Select Action")
            val pictureDialogItems = arrayOf(
                "selectionner une photo existante",
                "prendre une nouvelle photo"
            )
            pictureDialog.setItems(pictureDialogItems,
                DialogInterface.OnClickListener { dialog, which ->
                    when (which) {
                        0 -> {
                            val intent = Intent(Intent.ACTION_PICK)

                            intent.type = "image/*"
                            startActivityForResult(intent, REQUEST_CODE_GALLERY)
                        }
                        1 -> {
                            Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
                                takePictureIntent.resolveActivity(packageManager)?.also {
                                    startActivityForResult(takePictureIntent, REQUEST_CODE_CAMERA)
                                }

                            }
                        }
                    }
                })
            pictureDialog.show()
        }
contact()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        data?.data.let {

            ContactButton.setImageURI(it)
        }
    }

    companion object {
        private const val REQUEST_CODE_GALLERY = 73
        private const val REQUEST_CODE_CAMERA = 88

    }


    private fun setupPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_CONTACTS),
                1
            )
        }
    }



    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        val REQUEST_CODE_CONTACT = 73
        when (requestCode) {
            REQUEST_CODE_CONTACT -> {

                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {

                    Log.i(TAG, "Permission has been denied by user")
                } else {
                    Log.i(TAG, "Permission has been granted by user")
                }
            }
        }
    }

    private fun contact() {
        contactRecycler.adapter = ContactActivityAdapter(contactListe.sorted())
        contactRecycler.layoutManager = LinearLayoutManager(this)
    }
}