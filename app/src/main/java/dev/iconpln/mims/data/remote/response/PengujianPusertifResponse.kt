package dev.iconpln.mims.data.remote.response

import com.google.gson.annotations.SerializedName

data class PengujianPusertifResponse(

    @field:SerializedName("totalRow")
    val totalRow: Int,

    @field:SerializedName("data")
    val data: List<DataItemPusertif>,

    @field:SerializedName("timestamp")
    val timestamp: String,

    @field:SerializedName("status")
    val status: Int
)

data class DataItemPusertif(

    @field:SerializedName("qty_tdk_lolos")
    val qtyTdkLolos: String,

    @field:SerializedName("form_ba")
    val formBa: String,

    @field:SerializedName("no_khs")
    val noKhs: String,

    @field:SerializedName("qty_siap")
    val qtySiap: String,

    @field:SerializedName("no_dokumen_ust")
    val noDokumenUst: String,

    @field:SerializedName("flag_petugas")
    val flagPetugas: String,

    @field:SerializedName("alasan_reject")
    val alasanReject: String,

    @field:SerializedName("pengujian_ke")
    val pengujianKe: String,

    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("form_ust")
    val formUst: String,

    @field:SerializedName("create_date")
    val createDate: String,

    @field:SerializedName("update_by")
    val updateBy: String,

    @field:SerializedName("tanggal_pengajuan")
    val tanggalPengajuan: String,

    @field:SerializedName("tanggal_uji")
    val tanggalUji: String,

    @field:SerializedName("nama_pabrikan")
    val namaPabrikan: String,

    @field:SerializedName("usulan_koreksi")
    val usulanKoreksi: String,

    @field:SerializedName("kd_pabrikan")
    val kdPabrikan: String,

    @field:SerializedName("qty_material")
    val qtyMaterial: String,

    @field:SerializedName("status_uji")
    val statusUji: String,

    @field:SerializedName("no_pengujian")
    val noPengujian: String,

    @field:SerializedName("created_by")
    val createdBy: String,

    @field:SerializedName("update_date")
    val updateDate: String,

    @field:SerializedName("qty_lolos")
    val qtyLolos: String,

    @field:SerializedName("link_pengajuan_ust")
    val linkPengajuanUst: String,

    @field:SerializedName("tanggal_ust")
    val tanggalUst: String
)
