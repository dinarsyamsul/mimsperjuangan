package dev.iconpln.mims.ui.scan

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import dev.iconpln.mims.databinding.ActivityResponseScanBinding

@AndroidEntryPoint
class ResponseScanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResponseScanBinding
    private val viewModel: ScanViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResponseScanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        val data = intent.extras
        if (data != null) {
            binding.tvResult.text = data.getString(EXTRA_SN)
        }

        val sn = data?.getString(EXTRA_SN)

        if (sn != null) {
            viewModel.getDetailBySN(sn)
        }

//        binding.btnsimpan.setOnClickListener {
//            val intent = Intent(this@ResponseScanActivity, DetailDataAtributeMaterialActivity::class.java)
//            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//            startActivity(intent)
//        }

        viewModel.snResponse.observe(this) { data ->
            if (data.message == "Success") {
                data.detailSN.forEach {
                    binding.apply {
                        tvResult.text = it.serialNumber
                        tvNoMaterial.text = it.nomorMaterial
                        tvKodePabrik.text = it.kodePabrikan
                        tvNamaPabrik.text = it.namaPabrikan
                        tvTglProduksi.text = it.tglProduksi
                        tvSpekMaterial.text = it.spesifikasiMaterial
                        tvKatMaterial.text = it.kategoriMaterial
                        tvMasaGaransi.text = it.masaGaransi
                        tvNomorSert.text = it.nomorSertMetrologi
                        tvNoProduksi.text = it.noProduksi
                        tvNoPO.text = it.noPO
                    }
                }
            }
        }
    }

//    override fun onBackPressed() {
//        super.getOnBackPressedDispatcher()
//        val intent = Intent(this@ResponseScanActivity, DashboardPabrikanActivity::class.java)
//        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
//        startActivity(intent)
//    }

    companion object {
        const val EXTRA_SN = "extra_sn"
    }
}