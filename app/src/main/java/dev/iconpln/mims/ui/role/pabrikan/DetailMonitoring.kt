package dev.iconpln.mims.ui.role.pabrikan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.iconpln.mims.R

class DetailMonitoring : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_monitoring)
    }

    companion object {
        const val EXTRA_SN = "extra_sn"
    }
}