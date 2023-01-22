package dev.iconpln.mims.ui.role.pabrikan.arttribute_material

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import dev.iconpln.mims.databinding.ActivityDetailDataAtributeMaterialBinding

@AndroidEntryPoint
class DetailDataAtributeMaterialActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailDataAtributeMaterialBinding
    private val materialViewModel: MaterialViewModel by viewModels()
    private lateinit var rvAdapter: ListDetailMaterialAdapter
    private var noProduksi: String? = ""
    private var noMaterial: String? = ""
    private var serialNumber: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailDataAtributeMaterialBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rvAdapter = ListDetailMaterialAdapter()

        binding.apply {
            rvDetailMaterial.layoutManager = LinearLayoutManager(this@DetailDataAtributeMaterialActivity)
            rvDetailMaterial.adapter = rvAdapter
        }

        val data = intent.extras
        if (data != null) {
            Toast.makeText(this, "Data terpanggil: $data", Toast.LENGTH_SHORT).show()
            Log.d("DetailAtribut", "cek kiriman data: $data")
        }

        val sn = data?.getString(EXTRA_SN)
        if (sn != null) {
            Log.d("DetailAtribut", "cek kiriman data lagi: $sn")
            materialViewModel.getDetailMaterial("",sn,"")
        }

        materialViewModel.detailMaterialResponse.observe(this){
            rvAdapter.setData(it.data)
        }
    }

    companion object {
        const val EXTRA_SN = "extra_sn"
    }
}