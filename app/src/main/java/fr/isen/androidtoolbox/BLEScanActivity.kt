package fr.isen.androidtoolbox

import android.Manifest
import android.app.Activity
import android.bluetooth.*
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.location.LocationProvider
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.location.LocationRequest
import kotlinx.android.synthetic.main.activity_b_l_e_scan.*
import kotlin.collections.ArrayList


class BLEScanActivity: AppCompatActivity() {

    private lateinit var handler: Handler
    private var mScanning: Boolean = false
    private lateinit var adapter: BLEScanActivityAdapter
    private val devices = ArrayList<ScanResult>()

    var bluetoothGatt: BluetoothGatt? = null

    private val bluetoothAdapter: BluetoothAdapter? by lazy(LazyThreadSafetyMode.NONE) {
        val bluetoothManager = getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        bluetoothManager.adapter
    }

    private val isBLEEnabled: Boolean
        get() = bluetoothAdapter?.isEnabled == true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_b_l_e_scan)
        setupPermissions()
        scanButton.setOnClickListener {
            when {
                isBLEEnabled -> {
                    //init scan
                    // scanButton.setImageResource(android.R.drawable.ic_media_pause)
                        //textView12.text = "Scan en cours ..."
                        initBLEScan()
                        initScan()
                        progressBar3.incrementProgressBy(11)
                }
                bluetoothAdapter != null -> {
                    //ask for permission
                    val enableBTIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                    startActivityForResult(enableBTIntent, REQUEST_ENABLE_BT)

                }
                else -> {
                    //device is not compatible with your device
                    Toast.makeText(this, "phone incompatible with ble", Toast.LENGTH_SHORT).show()
                }
            }
        }
        BLERecycler.adapter = BLEScanActivityAdapter(devices, ::onDeviceClicked)
        BLERecycler.layoutManager = LinearLayoutManager(this)
    }


    private fun initScan() {
        progressBar3.visibility = View.VISIBLE

        handler = Handler()
        scanLeDevice(true)
    }

    private fun scanLeDevice(enable: Boolean) {
        bluetoothAdapter?.bluetoothLeScanner?.apply {
            if (enable) {
                progressBar3.incrementProgressBy(19)
                progressBar3.incrementSecondaryProgressBy(19)

                Log.w("BLE", "Scanning for devices")
                handler.postDelayed({
                    mScanning = false
                    stopScan(leScanCallback)
                }, SCAN_PERIOD)
                mScanning = true
                startScan(leScanCallback)
                adapter.clearResults()
                adapter.notifyDataSetChanged()
            } else {
                mScanning = false
                stopScan(leScanCallback)
            }
        }
    }

    private val leScanCallback = object : ScanCallback() {
        override fun onScanResult(callbackType: Int, result: ScanResult) {
            super.onScanResult(callbackType, result)
            Log.w("BLE", "${result.device}")
            runOnUiThread {
                adapter.addDeviceToList(result)
                adapter.notifyDataSetChanged()
            }
        }
    }
    private fun setupPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                1
            )
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_ADMIN)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.BLUETOOTH_ADMIN),
                1
            )
        }
    }

    private fun initBLEScan() {
        adapter = BLEScanActivityAdapter(
            arrayListOf(),
            ::onDeviceClicked
        )
        BLERecycler.adapter = adapter
        BLERecycler.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))

        handler = Handler()

        scanLeDevice(true)
        BLERecycler.setOnClickListener {
            scanLeDevice(!mScanning)
        }
    }

    private fun onDeviceClicked(device: BluetoothDevice) {
      //  val intent = Intent(this, BluetoothDetails::class.java)
        intent.putExtra("ble_device", device)
        startActivity(intent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_ENABLE_BT) {
            if (resultCode == Activity.RESULT_OK) {
                if (isBLEEnabled) {
                    Toast.makeText(this, "Bluetooth has been enabled", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Bluetooth has been disabled", Toast.LENGTH_SHORT).show()
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(this, "Bluetooth enabling has been canceled", Toast.LENGTH_SHORT)
                    .show()
            }
        }

    }

    override fun onStop() {
        super.onStop()
        scanLeDevice(false)
    }

    companion object {
        private const val SCAN_PERIOD: Long = 60000
        private const val REQUEST_ENABLE_BT = 44
    }
}