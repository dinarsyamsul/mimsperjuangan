package dev.iconpln.mims.ui.role.pabrikan.tracking_history

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions
import dagger.hilt.android.AndroidEntryPoint
import dev.iconpln.mims.R
import dev.iconpln.mims.data.remote.response.DataItemDetailHistoryTracking
import dev.iconpln.mims.data.remote.response.DataItemHistory
import dev.iconpln.mims.databinding.ActivityTrackingBinding
import dev.iconpln.mims.databinding.DataMaterialTrackingBinding
import dev.iconpln.mims.ui.role.pabrikan.DashboardPabrikanActivity
import dev.iconpln.mims.ui.role.pabrikan.DetailMonitoring.Companion.EXTRA_SN
import dev.iconpln.mims.ui.scan.CustomScanActivity
import dev.iconpln.mims.utils.MemuatData

@AndroidEntryPoint
class TrackingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTrackingBinding
    private val historyTrackingViewModel: HistoryTrackingViewModel by viewModels()
    private lateinit var rvAdapter: ListHistoryTrackingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTrackingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            val intent = Intent(this@TrackingActivity, DashboardPabrikanActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        rvAdapter = ListHistoryTrackingAdapter()

        binding.apply {
            rvTrackinghistory.layoutManager = LinearLayoutManager(this@TrackingActivity)
            rvTrackinghistory.adapter = rvAdapter
        }

        historyTrackingViewModel.getHistoryTracking()

        historyTrackingViewModel.historyTrackingResponse.observe(this){
            rvAdapter.setData(it.data)
        }
        showSelectedHistory()

        binding.btnScnQRtrckg.setOnClickListener {
            openScanner()

        }
    }

    private fun showSelectedHistory(){
        rvAdapter.setOnItemClickCallBack(object : ListHistoryTrackingAdapter.OnItemClickCallBack{
            override fun onItemClicked(data: DataItemHistory) {
                val toDetailHistory =
                    Intent(this@TrackingActivity, DataDetailTrackingActivity::class.java)
                toDetailHistory.putExtra(DataDetailTrackingActivity.EXTRA_SN, data.serialNumber)
                startActivity(toDetailHistory)
            }
        })
        }

    private fun openScanner() {
        val scan = ScanOptions()
        scan.setDesiredBarcodeFormats(ScanOptions.ALL_CODE_TYPES)
        scan.setCameraId(0)
        scan.setBeepEnabled(true)
        scan.setBarcodeImageEnabled(true)
        scan.captureActivity = CustomScanActivity::class.java
        barcodeLauncher.launch(scan)
    }

    private val barcodeLauncher = registerForActivityResult(
        ScanContract()
    ) { result: ScanIntentResult ->
        if (!result.contents.isNullOrEmpty()) {

//            viewModel.getDetailBySN(result.contents)

            val intent = Intent(this, MemuatData::class.java)
            intent.putExtra(MemuatData.EXTRA_SN_HISTORY, result.contents)
            startActivity(intent)
            Toast.makeText(this, "Serial Number: ${result.contents}", Toast.LENGTH_LONG).show()
        } else {
//             CANCELED
        }
    }
    }
