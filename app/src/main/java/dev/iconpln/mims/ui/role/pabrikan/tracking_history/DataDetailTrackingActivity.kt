package dev.iconpln.mims.ui.role.pabrikan.tracking_history

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import dev.iconpln.mims.R
import dev.iconpln.mims.databinding.ActivityDataDetailTrackingBinding

@AndroidEntryPoint
class DataDetailTrackingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDataDetailTrackingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDataDetailTrackingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {

            onBackPressedDispatcher.onBackPressed()
        }
    }

}