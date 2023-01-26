package dev.iconpln.mims.ui.role.pabrikan.tracking_history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import dev.iconpln.mims.data.remote.response.DataItemDetailHistoryTracking
import dev.iconpln.mims.data.remote.response.DataItemHistory
import dev.iconpln.mims.databinding.ItemDataTrackingBinding
import dev.iconpln.mims.databinding.ItemDetailTrackingBinding

class ListHistoryTrackingAdapter() :
    RecyclerView.Adapter<ListHistoryTrackingAdapter.ListViewHolder>() {

    private val listHistoryTracking = ArrayList<DataItemHistory>()
    private var onItemClickCallback: OnItemClickCallBack? = null

    fun setData(items: List<DataItemHistory>) {
        val diffCallback = HistoryTrackingDiffCallback(listHistoryTracking, items)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        listHistoryTracking.clear()
        listHistoryTracking.addAll(items)
        diffResult.dispatchUpdatesTo(this)
    }

    fun setOnItemClickCallBack(onItemClickCallback: OnItemClickCallBack) {
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

    class ListViewHolder(val itemBinding: ItemDataTrackingBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: DataItemHistory) {
            with(itemBinding) {
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

class ListDetailHistoryTrackingAdapter() :
    RecyclerView.Adapter<ListDetailHistoryTrackingAdapter.ListViewHolder>() {

    private val listDetailHistoryTracking = ArrayList<DataItemDetailHistoryTracking>()
    private var onItemClickCallback: OnItemClickCallBack? = null

    fun setData(items: List<DataItemDetailHistoryTracking>) {
        val diffCallback = DetailHistoryTrackingDiffCallback(listDetailHistoryTracking, items)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        listDetailHistoryTracking.clear()
        listDetailHistoryTracking.addAll(items)
        diffResult.dispatchUpdatesTo(this)
    }

    fun setOnItemClickCallBack(onItemClickCallback: OnItemClickCallBack) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val itemBinding = ItemDetailTrackingBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return ListViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listDetailHistoryTracking[position])

        holder.itemView.setOnClickListener {
            onItemClickCallback?.onItemClicked(listDetailHistoryTracking[holder.bindingAdapterPosition])
        }
    }

    override fun getItemCount(): Int = listDetailHistoryTracking.size

    class ListViewHolder(val itemBinding: ItemDetailTrackingBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: DataItemDetailHistoryTracking) {
            with(itemBinding) {
                tvIsiDtlTrckSerialNumber.text = item.serialNumber
                tvIsiDtlTrckKeteranganHistory.text = item.keteranganHistory
                tvIsiDtlTrckStatus.text = item.status
                tvIsiDtlTrckDate.text = item.date
                tvIsiDtlTrckNomorTransaksi.text = item.nomorTransaksi
                tvIsiDtlTrckPlant.text = item.plant
                tvIsiDtlTrckStorLok.text = item.storLoc
                tvIsiDtlTrckMaterialGroup.text = item.materialGroup
                tvIsiDtlTrckId.text = item.id
            }
        }
    }

    interface OnItemClickCallBack {
        fun onItemClicked(data: DataItemDetailHistoryTracking)
    }
}