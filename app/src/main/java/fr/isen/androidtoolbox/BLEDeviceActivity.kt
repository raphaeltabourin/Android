package fr.isen.androidtoolbox

import android.annotation.SuppressLint
import android.bluetooth.*
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_b_l_e_device.*
import java.sql.ClientInfoStatus

class BLEDeviceActivity : AppCompatActivity() {

    private var bluetoothGatt : BluetoothGatt? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b_l_e_device)

       /* val device = intent.getParcelableExtra<BluetoothDevice>("ble_device")
        deviceName.text = device?.name?: getString(R.string.ble_scan_default_name)
        progressBar.visibility = View.VISIBLE
        divider.visibility = View.INVISIBLE
        connectToDevice(device)*/
    }

















/*
    private fun connectToDevice(device: BluetoothDevice?){
        bluetoothGatt = device?.connectGatt(this,true,object: BluetoothGattCallback()){
            override fun onConnectionStateChange(gatt: BluetoothGatt?,status: Int, newState: Int){
                super.onConnectionStateChange(gatt,status,newState)
                onConnectionStateChange(newState , gatt)
            }
            override fun onServiceDiscovered(gatt: BluetoothGatt?, status: Int) {
                super.onServiceDiscovered(gatt, status)
                onServiceDiscovered(gatt)
            }
           /* override fun onCharacteristicRead(gatt: BluetoothGatt?, characteristic: BluetoothGattCharacteristic?,status: Int){
                super.onCharacteristicread(gatt,characteristic,status)
                runOnUiThread{
                    adapter.updateFromChangedCharacteristic(characteristic)
                adapter}*/
            }
        }
    }*/
}
