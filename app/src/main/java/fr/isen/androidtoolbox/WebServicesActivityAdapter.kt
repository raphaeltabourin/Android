import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_contact_cell.view.*
import kotlinx.android.synthetic.main.activity_web_services_cell.view.*
import android.content.Context

import android.view.LayoutInflater
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import fr.isen.androidtoolbox.R
import fr.isen.androidtoolbox.Result
import kotlinx.android.synthetic.main.activity_web_services.*

class WebServicesActivityAdapter (var c: Context, var contactList: List<Result>) : RecyclerView.Adapter<WebServicesActivityAdapter.WebServicesHolder>(){

    //VIEWHOLDER IS INITIALIZED
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WebServicesHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.activity_web_services_cell, parent,false)
        return WebServicesHolder(v)
    }

    //DATA IS BOUND TO VIEWS
    override fun onBindViewHolder(holder: WebServicesHolder, position: Int) {
        val contact = contactList[position]
        holder.nameTxt.text =contact.name.title.capitalize()+" "+ contact.name.last.toUpperCase() + " "+contact.name.first.capitalize()
        holder.location.text=contact.location.city + "," + contact.location.state
        holder.mail.text=contact.email
        Picasso.get().load(contact.picture.large).into(holder.img)
        //holder.img.setImageResource(teacher.picture.large)
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    class WebServicesHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var img: ImageView
        var nameTxt: TextView

      var  location= itemView.adresse
        var mail=itemView.mail
        init {

            nameTxt = itemView.nom
            img = itemView.avatar

        }




    }
}