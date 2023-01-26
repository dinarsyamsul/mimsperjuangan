package dev.iconpln.mims.data.remote.response

import com.google.gson.annotations.SerializedName

data class DetailHistoryTrackingResponse(

    @field:SerializedName("data")
    val data: List<DataItemDetailHistoryTracking>,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("timestamp")
    val timestamp: String,

    @field:SerializedName("status")
    val status: Int
)

data class DataItemDetailHistoryTracking(

    @field:SerializedName("date")
    val date: String,

    @field:SerializedName("keterangan_history")
    val keteranganHistory: String,

    @field:SerializedName("stor_loc")
    val storLoc: String,

    @field:SerializedName("material_group")
    val materialGroup: String,

    @field:SerializedName("plant")
    val plant: String,

    @field:SerializedName("serial_number")
    val serialNumber: String,

    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("status")
    val status: String,

    @field:SerializedName("nomor_transaksi")
    val nomorTransaksi: String
)
