package dev.iconpln.mims.data.remote.response

import com.google.gson.annotations.SerializedName

data class HistoryTrackingResponse(

	@field:SerializedName("data")
	val data: List<DataItemHistory>,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("timestamp")
	val timestamp: String,

	@field:SerializedName("status")
	val status: Int
)

data class DataItemHistory(

	@field:SerializedName("nomor_material")
	val nomorMaterial: String,

	@field:SerializedName("mmc")
	val mmc: String,

	@field:SerializedName("total_row")
	val totalRow: String,

	@field:SerializedName("spesifikasi")
	val spesifikasi: String,

	@field:SerializedName("plant")
	val plant: String,

	@field:SerializedName("kd_pabrikan")
	val kdPabrikan: String,

	@field:SerializedName("serial_number")
	val serialNumber: String,

	@field:SerializedName("spln")
	val spln: String,

	@field:SerializedName("kategori")
	val kategori: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("no_produksi")
	val noProduksi: String,

	@field:SerializedName("no_packaging")
	val noPackaging: String
)
