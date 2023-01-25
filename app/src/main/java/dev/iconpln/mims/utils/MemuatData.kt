package dev.iconpln.mims.utils

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import dev.iconpln.mims.ui.role.pabrikan.tracking_history.HasilTrackingScanActivity
import dev.iconpln.mims.ui.scan.HasilScan
import dev.iconpln.mims.R
import dev.iconpln.mims.ui.role.pabrikan.tracking_history.HistoryTrackingViewModel
import dev.iconpln.mims.ui.scan.ScanViewModel

@AndroidEntryPoint
class MemuatData : AppCompatActivity() {

    private val viewModel: ScanViewModel by viewModels()
    private val viewModelTracking: HistoryTrackingViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memuat_data)

        val data = intent.extras
        val sn = data?.getString(EXTRA_SN)

        val snTracking = data?.getString(EXTRA_SN_HISTORY)

        if (sn != null) {
            viewModel.getDetailBySN(sn)
        }

        if (snTracking != null) {
            viewModelTracking.getScanTracking(snTracking)
        }

        viewModel.snResponse.observe(this) { data ->
            Log.d("customactivty", "cek data ${data.message}")
            if (data.message == "Success") {
                data.detailSN.forEach { result ->
                    val intent = Intent(this@MemuatData, HasilScan::class.java)
                    intent.putExtra(HasilScan.EXTRA_SN, result.serialNumber)
                    startActivity(intent)
                }
            }
        }

        viewModel.errorMessage.observe(this) {
            Log.d("ResponseActivity", "cek response $it")
            if (it != null) {
                val intent = Intent(this@MemuatData, NotFound::class.java)
                startActivity(intent)
                Toast.makeText(this, "Data serial number tidak sesuai", Toast.LENGTH_LONG).show()

            }
        }

        viewModelTracking.scanTrackingHistory.observe(this) { data ->
            if (data.message == "success") {
                data.data.forEach { result ->
                    val intent = Intent(this@MemuatData, HasilTrackingScanActivity::class.java)
                    intent.putExtra(
                        HasilTrackingScanActivity.EXTRA_SN_TRACKING,
                        result.serialNumber
                    )
                    startActivity(intent)
                }
            }
        }

        viewModelTracking.errorMessage.observe(this) {
            if (it != null) {
                val intent = Intent(this@MemuatData, NotFound::class.java)
                startActivity(intent)
                Toast.makeText(this, "Data Serial Number Tracking Tidak Sesuai", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    companion object {
        const val EXTRA_SN = "extra_sn"
        const val EXTRA_SN_HISTORY = "extra_sn_history"
    }
}