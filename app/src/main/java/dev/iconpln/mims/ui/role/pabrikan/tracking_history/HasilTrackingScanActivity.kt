package dev.iconpln.mims.ui.role.pabrikan.tracking_history

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import dev.iconpln.mims.databinding.ActivityHasilTrackingScanBinding

@AndroidEntryPoint
class HasilTrackingScanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHasilTrackingScanBinding
    private val viewModelScan: HistoryTrackingViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHasilTrackingScanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataSnHasilTracking = intent.extras

        val snTracking = dataSnHasilTracking?.getString(EXTRA_SN_TRACKING)

        if (snTracking != null) {
            viewModelScan.getScanTracking(snTracking)
        }

        viewModelScan.scanTrackingHistory.observe(this) { data ->
            if (data.message == "success")
                Toast.makeText(this, "Scan Berhasil ${data.data}", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        const val EXTRA_SN_TRACKING = "extra_sn_tracking"
    }
}