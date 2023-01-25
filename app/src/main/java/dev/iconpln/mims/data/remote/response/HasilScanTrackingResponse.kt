package dev.iconpln.mims.data.remote.response

import com.google.gson.annotations.SerializedName

data class HasilScanTrackingResponse(

	@field:SerializedName("data")
	val data: List<DataItemScanTracking>,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("timestamp")
	val timestamp: String,

	@field:SerializedName("status")
	val status: Int
)

data class DataItemScanTracking(

	@field:SerializedName("serial_number")
	val serialNumber: String,

	@field:SerializedName("property_value")
	val propertyValue: String,

	@field:SerializedName("property_name")
	val propertyName: String
)
