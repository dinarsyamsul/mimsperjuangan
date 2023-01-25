package dev.iconpln.mims.ui.role.pabrikan.tracking_history

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ListView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import dev.iconpln.mims.data.remote.response.DataItemHistory
import dev.iconpln.mims.data.remote.response.DataMonitoringPO
import dev.iconpln.mims.data.remote.response.HistoryTrackingResponse
import dev.iconpln.mims.databinding.ItemDataTrackingBinding
import dev.iconpln.mims.ui.role.pabrikan.purchase_order.ListNoPoAdapter
import dev.iconpln.mims.ui.role.pabrikan.purchase_order.MonitoringPODiffCallback

class ListHistoryTrackingAdapter(): RecyclerView.Adapter<ListHistoryTrackingAdapter.ListViewHolder>() {

    private val listHistoryTracking = ArrayList<DataItemHistory>()
    private var onItemClickCallback: OnItemClickCallBack? = null

    fun setData(items: List<DataItemHistory>) {
        val diffCallback = HistoryTrackingDiffCallback(listHistoryTracking, items)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        listHistoryTracking.clear()
        listHistoryTracking.addAll(items)
        diffResult.dispatchUpdatesTo(this)
    }

    fun setOnItemClickCallBack(onItemClickCallback: OnItemClickCallBack){
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val itemBinding = ItemDataTrackingBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return ListViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
     holder.bind(listHistoryTracking[position])

        holder.itemView.setOnClickListener {
            onItemClickCallback?.onItemClicked(listHistoryTracking[holder.bindingAdapterPosition])
        }
    }

    override fun getItemCount(): Int = listHistoryTracking.size

    class ListViewHolder(val itemBinding: ItemDataTrackingBinding): RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: DataItemHistory){
            with(itemBinding){
                txtSerialNumber.text = item.serialNumber
                txtMaterial.text = item.nomorMaterial
                txtIsiBatch.text = item.noProduksi
                txtIsiSPLN.text = item.spln
                txtIsispesifikasi.text = item.spesifikasi
                txtIsiPackaging.text = item.noPackaging
                txtIsikategori.text = item.kategori
            }
        }
    }

    interface OnItemClickCallBack {
        fun onItemClicked(data: DataItemHistory)
    }
}