package dev.iconpln.mims.ui.role.pabrikan.tracking_history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.iconpln.mims.data.remote.response.DetailSN
import dev.iconpln.mims.data.remote.response.HasilScanTrackingResponse
import dev.iconpln.mims.data.remote.response.HistoryTrackingResponse
import dev.iconpln.mims.data.remote.response.MonitoringPOResponse
import dev.iconpln.mims.data.remote.service.ApiService
import kotlinx.coroutines.*
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class HistoryTrackingViewModel @Inject constructor (private val apiService: ApiService) : ViewModel() {

    private var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _historyTrackingResponse = MutableLiveData<HistoryTrackingResponse>()
    val historyTrackingResponse: LiveData<HistoryTrackingResponse> = _historyTrackingResponse

    private val _scanTrackingHistory = MutableLiveData<HasilScanTrackingResponse>()
    val scanTrackingHistory: LiveData<HasilScanTrackingResponse> = _scanTrackingHistory

    fun getHistoryTracking(pageIn: Int? = 1, pageSize: Int? = 5) {
        _isLoading.value = true
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = apiService.getHistoryTracking(pageIn, pageSize)

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    _isLoading.value = false
                    val dataResult = response.body()
                    _historyTrackingResponse.postValue(dataResult)
                } else {
                    _isLoading.value = false
                    val error = response.errorBody()?.toString()
                    onError("Error : ${error?.let { getErrorMessage(it) }}")
                }
            }
        }
    }

     fun getScanTracking(sn: String) {
        _isLoading.value = true
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = apiService.getSnScanHistory(sn)

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    _isLoading.value = false
                    val dataResult = response.body()
                    _scanTrackingHistory.postValue(dataResult)
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