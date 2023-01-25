package dev.iconpln.mims.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "token_sessions")

class SessionManager @Inject constructor(
    @ApplicationContext private val context: Context
) {

    val user_token: Flow<String?>
        get() = context.dataStore.data.map { pref ->
            pref[USER_TOKEN]
        }

    val device_token: Flow<String?>
        get() = context.dataStore.data.map { pref ->
            pref[DEVICE_TOKEN]
        }

    val role_id: Flow<String?>
        get() = context.dataStore.data.map { pref ->
            pref[ROLE_ID]
        }

    val user_name: Flow<String?>
        get() = context.dataStore.data.map { pref ->
            pref[USER_NAME]
        }

    val nama_cabang: Flow<String?>
        get() = context.dataStore.data.map { pref ->
            pref[NAMA_CABANG]
        }

    suspend fun saveAuthToken(user_token: String, device_token: String, role_id: String, user_name: String, nama_cabang: String) {
        context.dataStore.edit { pref ->
            pref[USER_TOKEN] = user_token
            pref[DEVICE_TOKEN] = device_token
            pref[ROLE_ID] = role_id
            pref[USER_NAME] = user_name
            pref[NAMA_CABANG] = nama_cabang
        }
    }

    suspend fun clearUserToken() {
        context.dataStore.edit { pref ->
            pref.remove(USER_TOKEN)
            pref.remove(ROLE_ID)
            pref.remove(USER_NAME)
            pref.remove(NAMA_CABANG)
//            pref.remove(DEVICE_TOKEN)
        }
    }

    companion object {
        private val USER_TOKEN = stringPreferencesKey("user_token")
        private val DEVICE_TOKEN = stringPreferencesKey("device_token")
        private val ROLE_ID = stringPreferencesKey("role_id")
        private val USER_NAME = stringPreferencesKey("user_name")
        private val NAMA_CABANG = stringPreferencesKey("nama_cabang")
    }
}