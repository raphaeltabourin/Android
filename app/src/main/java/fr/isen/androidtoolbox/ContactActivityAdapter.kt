package fr.isen.androidtoolbox

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import android.widget.TextView
import fr.isen.androidtoolbox.R.layout.activity_contact_cell
import kotlinx.android.synthetic.main.activity_contact.view.*
import kotlinx.android.synthetic.main.activity_contact_cell.view.*
import kotlinx.android.synthetic.main.activity_form.view.*

class ContactActivityAdapter(private val liste: List<String>?) : RecyclerView.Adapter<ContactActivityAdapter.ContactViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder =
        ContactViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(activity_contact_cell, parent, false)

        )

    override fun getItemCount(): Int
    {
        return liste!!.size

    }
    class ContactViewHolder(contactView: View) : RecyclerView.ViewHolder(contactView) {
        val nomContact: TextView = contactView.nomContact
    }
    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.nomContact.text = liste?.get(position) ?:"coucou"
    }


}

// sur le net contacteur view holder on cree un new newholder pour lier au nouvel layout