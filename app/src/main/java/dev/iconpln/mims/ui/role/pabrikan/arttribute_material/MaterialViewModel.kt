package dev.iconpln.mims.ui.role.pabrikan.arttribute_material

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.iconpln.mims.data.remote.response.MaterialResponse
import dev.iconpln.mims.data.remote.service.ApiService
import kotlinx.coroutines.*
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class MaterialViewModel @Inject constructor(private val apiService: ApiService) : ViewModel() {

    private var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _materialResponse = MutableLiveData<MaterialResponse>()
    val materialResponse: LiveData<MaterialResponse> = _materialResponse

    private val _detailMaterialResponse = MutableLiveData<MaterialResponse>()
    val detailMaterialResponse: LiveData<MaterialResponse> = _detailMaterialResponse


    fun getAllMaterial(
        kategori: String? = "",
        tahun: String? = "",
        filter: String?,
        pageIn: Int? = 1,
        pageSize: Int? = 100
    ) {
        _isLoading.value = true
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = apiService.getMaterial(kategori, tahun, filter, pageIn, pageSize)

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    _isLoading.value = false
                    val dataResult = response.body()
                    Log.d("MaterialViewModel", "cek data atribut $dataResult")
                    _materialResponse.postValue(dataResult)
                } else {
                    _isLoading.value = false
                    val error = response.errorBody()?.toString()
                    onError("Error : ${error?.let { getErrorMessage(it) }}")
                }
            }
        }
    }

    fun getDetailMaterial(
        noProduksi: String? = "",
        noMaterial: String? = "",
        tahun: String? = "",
        kodeGerak: String? = "",
        serialNumber: String? = "",
        pageIn: Int? = 1,
        pageSize: Int? = 100
    ) {
        _isLoading.value = true
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response =
                apiService.getDetailMaterial(noProduksi, noMaterial, tahun, kodeGerak, serialNumber, pageIn, pageSize)

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    _isLoading.value = false
                    val dataResult = response.body()
                    _detailMaterialResponse.postValue(dataResult)
                } else {
                    _isLoading.value = false
                    val error = response.errorBody()?.toString()
                    onError("Error : ${error?.let { getErrorMessage(it) }}")
                }
            }
        }
    }

    private fun onError(message: String) {
        _isLoading.postValue(false)
        _errorMessage.postValue(message)
    }

    private fun getErrorMessage(raw: String): String {
        val obj = JSONObject(raw)
        return obj.getString("message")
    }
}