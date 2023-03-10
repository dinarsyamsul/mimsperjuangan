package dev.iconpln.mims.ui.role.pabrikan.tracking_history

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import dev.iconpln.mims.databinding.ActivityDataDetailTrackingBinding

@AndroidEntryPoint
class DataDetailTrackingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDataDetailTrackingBinding
    private val detailHistoryTrackingModel: HistoryTrackingViewModel by viewModels()
    private lateinit var rvAdapter: ListDetailHistoryTrackingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDataDetailTrackingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rvAdapter = ListDetailHistoryTrackingAdapter()

        binding.apply {
            rvDetailDataHistoryTracking.layoutManager =
                LinearLayoutManager(this@DataDetailTrackingActivity)
            rvDetailDataHistoryTracking.adapter = rvAdapter
        }

        val data = intent.extras
        if (data != null) {
            Log.d("DataDetailHistory", "cek kiriman data : $data")
        }

        val noSerial = data?.getString(EXTRA_SN)
        if (noSerial != null) {
            detailHistoryTrackingModel.getDetailHistoryTracking(noSerial)
        }

        detailHistoryTrackingModel.detailHistoryTracking.observe(this) {
            rvAdapter.setData(it.data)
        }

        detailHistoryTrackingModel.errorMessage.observe(this) {
            if (it != null) {
                rvAdapter.setData(listOf())
            }
        }

        detailHistoryTrackingModel.isLoading.observe(this) {
            if (it == true) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        }

        binding.btnBack.setOnClickListener {

            onBackPressedDispatcher.onBackPressed()
        }
    }

    companion object {
        const val EXTRA_SN = "serial_Number"
    }

}