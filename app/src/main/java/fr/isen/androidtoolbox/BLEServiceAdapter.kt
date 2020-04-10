

import android.bluetooth.BluetoothGattCharacteristic
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fr.isen.androidtoolbox.BLEService
import fr.isen.androidtoolbox.ExpandableGroup
import fr.isen.androidtoolbox.R


class BLEServiceAdapter(private val serviceList: MutableList<BLEService>) :
    ExpandableRecyclerViewAdapter<BLEServiceAdapter.ServiceViewHolder, BLEServiceAdapter.CharacteristicsViewHolder?>(groups) {
    class ServiceViewHolder(itemView:View) : GroupViewHolder(itemView){

    }
    class CharacteristicsViewHolder(itemView: View): ChildViewHolder(itemView)

    fun onCreateGroupViewHolder(parent: ViewGroup?, viewType: Int): ServiceViewHolder {
        val view: View = LayoutInflater.from(parent!!.context).inflate(R.layout.activity_b_l_e_device, parent, false)
        return ServiceViewHolder(view)
    }

    fun onCreateChildViewHolder(parent: ViewGroup?, viewType: Int): CharacteristicsViewHolder {
        val view: View = LayoutInflater.from(parent!!.context).inflate(R.layout.recycler_view_item_ble, parent, false)
        return CharacteristicsViewHolder(view)
    }

    fun onBindChildViewHolder(
        holder: CharacteristicsViewHolder, flatPosition: Int, group: ExpandableGroup<*>,
        childIndex: Int
    ) {
        val characteristic: BluetoothGattCharacteristic? = (group as BLEService).items?.get(childIndex)
       val uuid= characteristic?.uuid
    }

    fun onBindGroupViewHolder(
        holder: ServiceViewHolder, flatPosition: Int,
        group: ExpandableGroup<*>
    ) {
val title=group.title
    }
}

object groups {

}

open class GroupViewHolder(value: View) {

}

open class ExpandableRecyclerViewAdapter<T, U>(value: Any) {

}

open class ChildViewHolder(value: View) {

}
