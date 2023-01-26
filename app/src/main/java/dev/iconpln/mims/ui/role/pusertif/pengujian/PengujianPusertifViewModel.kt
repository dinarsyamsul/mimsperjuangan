package dev.iconpln.mims.ui.role.pusertif.pengujian

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.iconpln.mims.data.remote.response.PengujianPusertifResponse
import dev.iconpln.mims.data.remote.service.ApiService
import kotlinx.coroutines.*
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class PengujianPusertifViewModel @Inject constructor(private val apiService: ApiService) :
    ViewModel() {

    private var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _pengujianPusertifResponse = MutableLiveData<PengujianPusertifResponse>()
    val pengujianPusertifResponse: LiveData<PengujianPusertifResponse> = _pengujianPusertifResponse

    fun getPengujianPusertif(
        noPengujian: String?,
        vendor: String?,
        filter: String?,
        pageIn: Int? = 1,
        pageSize: Int? = 20
    ) {
        _isLoading.value = true
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response =
                apiService.getPengujianPusertif(noPengujian, vendor, filter, pageIn, pageSize)

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    _isLoading.value = false
                    val dataResult = response.body()
                    _pengujianPusertifResponse.postValue(dataResult)
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