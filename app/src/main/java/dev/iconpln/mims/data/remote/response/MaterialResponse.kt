package dev.iconpln.mims.data.remote.response

import com.google.gson.annotations.SerializedName

data class MaterialResponse(

	@field:SerializedName("totalRow")
	val totalRow: Int,

	@field:SerializedName("data")
	val data: List<DataItemMaterial>,

	@field:SerializedName("timestamp")
	val timestamp: String,

	@field:SerializedName("status")
	val status: Int
)

data class DataItemMaterial(

	@field:SerializedName("nomor_material")
	val nomorMaterial: String,

	@field:SerializedName("spesifikasi_material")
	val spesifikasiMaterial: String,

	@field:SerializedName("status_material")
	val statusMaterial: String,

	@field:SerializedName("tgl_produksi")
	val tglProduksi: String,

	@field:SerializedName("flag_delete")
	val flagDelete: String,

	@field:SerializedName("serial_number")
	val serialNumber: String,

	@field:SerializedName("spln")
	val spln: String,

	@field:SerializedName("masa_garansi")
	val masaGaransi: String,

	@field:SerializedName("no_produksi")
	val noProduksi: String,

	@field:SerializedName("no_packaging")
	val noPackaging: String,

	@field:SerializedName("kategori_material")
	val kategoriMaterial: String,

	@field:SerializedName("no_sert_metrologi")
	val noSertMetrologi: String,

	@field:SerializedName("tahun_produksi")
	val tahunProduksi: String,

	@field:SerializedName("sources")
	val sources: String
)