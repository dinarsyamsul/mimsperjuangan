package dev.iconpln.mims.ui.role.pabrikan.pengujian

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import dev.iconpln.mims.R
import dev.iconpln.mims.databinding.ActivityPengujianBinding
import dev.iconpln.mims.ui.role.pabrikan.DashboardPabrikanActivity
import java.util.*

@AndroidEntryPoint
class PengujianActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPengujianBinding
    private val pengujianViewModel: PengujianViewModel by viewModels()
    private lateinit var rvAdapter: ListPengujianAdapter
    private var noPengujian: String? = ""
    private var status: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPengujianBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            val intent = Intent(this@PengujianActivity, DashboardPabrikanActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }

        val kategori = resources.getStringArray(R.array.kategori_pengujian)
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, kategori)
        binding.statusKategori.setAdapter(arrayAdapter)

        rvAdapter = ListPengujianAdapter()

//        list.addAll(getListTanggalUji())
//        showRecyclerList()

        binding.apply {
            rvPengujian.layoutManager = LinearLayoutManager(this@PengujianActivity)
            rvPengujian.adapter = rvAdapter
        }

        pengujianViewModel.getPengujian(noPengujian, status)

        pengujianViewModel.pengujianResponse.observe(this) {
            rvAdapter.setData(it.data)
        }

        pengujianViewModel.errorMessage.observe(this) {
            rvAdapter.setData(listOf())
        }

        binding.srcNoPengujian.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    val mQuery = query.uppercase(Locale.ROOT)
                    noPengujian = mQuery
                    pengujianViewModel.getPengujian(noPengujian, status)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    noPengujian = newText.uppercase(Locale.ROOT)
                    pengujianViewModel.getPengujian(noPengujian, status)
                }
                return false
            }
        })

        binding.statusKategori.setOnItemClickListener { _, _, _, _ ->
            status = binding.statusKategori.text.toString()
            if (status == "SEMUA") {
                status = ""
            }
            pengujianViewModel.getPengujian(noPengujian, status)
        }

        pengujianViewModel.isLoading.observe(this) {
            if (it == true) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        }
    }

    private fun getListTanggalUji(): ArrayList<PengujianRecycler> {
        val tanggalUji = resources.getStringArray(R.array.data_tahun)
        val listTanggalUji = ArrayList<PengujianRecycler>()
        for (i in tanggalUji.indices) {
            val tahun = PengujianRecycler(tanggalUji[i])
            listTanggalUji.add(tahun)
        }
        return listTanggalUji
    }

//    private fun showRecyclerList(){
//        binding.rvTanggalUji.layoutManager = LinearLayoutManager(this)
//        val listPengujianAdapter = ListPengujianAdapter(list)
//        binding.rvTanggalUji.adapter = listPengujianAdapter
//    }
}