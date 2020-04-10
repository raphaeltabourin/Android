package fr.isen.androidtoolbox

import android.bluetooth.BluetoothDevice
import android.bluetooth.le.ScanResult
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycler_view_item_ble.view.*

class BLEScanActivityAdapter(
    private val scanResults: ArrayList<ScanResult>,
    private val deviceClickListener: (BluetoothDevice) -> Unit
) :
    RecyclerView.Adapter<BLEScanActivityAdapter.DevicesViewHolder>() {

    class DevicesViewHolder(devicesView: View) : RecyclerView.ViewHolder(devicesView) {
        val deviceName: TextView = devicesView.nom_device
        val deviceMac: TextView = devicesView.status_device
        val deviceRSSI: TextView = devicesView.BTRssi
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DevicesViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item_ble, parent, false)

        return DevicesViewHolder(view)
    }

    override fun getItemCount(): Int = scanResults.size

    override fun onBindViewHolder(holder: DevicesViewHolder, position: Int) {
        holder.deviceName.text = scanResults[position].device.name ?: "Nom inconnu"
        holder.deviceMac.text = scanResults[position].device.address
        holder.deviceRSSI.text = scanResults[position].rssi.toString()
      /*  holder.layout.setOnClickListener {
            deviceClickListener.invoke(scanResults[position].device)
        }*/    }

    fun addDeviceToList(result: ScanResult) {
        val index = scanResults.indexOfFirst { it.device.address == result.device.address }
        if (index != -1) {
            scanResults[index] = result
        } else {
            scanResults.add(result)
        }
    }

    fun clearResults() {
        scanResults.clear()
    }
}