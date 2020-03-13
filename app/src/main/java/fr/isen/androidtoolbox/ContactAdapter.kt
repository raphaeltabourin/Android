package fr.isen.androidtoolbox

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ContactAdapter(val contacts: List<String>) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactAdapter.ContactViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int = contacts.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
// sur le net contacteur view holder on cree un new newholder pour lier au nouvel layout