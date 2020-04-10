package fr.isen.androidtoolbox

import android.bluetooth.BluetoothGattCharacteristic

class BLEService(title: String, items: List<BluetoothGattCharacteristic>) :
    ExpandableGroup<BluetoothGattCharacteristic>(title, items)