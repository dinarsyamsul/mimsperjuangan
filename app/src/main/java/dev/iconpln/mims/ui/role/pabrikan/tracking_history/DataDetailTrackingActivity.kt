package dev.iconpln.mims.ui.role.pabrikan.tracking_history

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import dev.iconpln.mims.R

@AndroidEntryPoint
class DataDetailTrackingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_detail_tracking)
    }

}