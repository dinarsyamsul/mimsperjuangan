package dev.iconpln.mims.ui.role.pabrikan.tracking_history

import androidx.recyclerview.widget.DiffUtil
import dev.iconpln.mims.data.remote.response.DataItemHistory

class HistoryTrackingDiffCallback (
        private val mOldHistoryTracking: List<DataItemHistory>,
        private val mNewHistoryTracking: List<DataItemHistory>
        ): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mOldHistoryTracking.size
    }

    override fun getNewListSize(): Int {
        return mNewHistoryTracking.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldHistoryTracking[oldItemPosition].serialNumber == mNewHistoryTracking[newItemPosition].serialNumber
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldTracking = mOldHistoryTracking[oldItemPosition]
        val newTracking = mNewHistoryTracking[newItemPosition]

        return oldTracking.serialNumber == newTracking.serialNumber &&
                oldTracking.nomorMaterial == newTracking.nomorMaterial &&
                oldTracking.spln == newTracking.spln &&
                oldTracking.noProduksi == newTracking.noProduksi &&
                oldTracking.spesifikasi == newTracking.spesifikasi &&
                oldTracking.kategori == newTracking.kategori &&
                oldTracking.noPackaging == newTracking.noPackaging

    }
}