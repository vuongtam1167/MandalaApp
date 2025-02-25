package com.example.mandala.Datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.mandala.Dataclass.MandalaDiaChi
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.mandalaDiaChiDataStore by preferencesDataStore(name = "mandala_diachi_store")

class MandalaDiaChiStore(private val context: Context) {

    private val gson = Gson()

    companion object {
        private val MANDALA_DIACHI_KEY = stringPreferencesKey("mandala_diachi_data")
    }

    /**
     * Lưu danh sách MandalaDiaChi dưới dạng JSON
     */
    suspend fun saveMandalaDiaChiList(diaChiList: List<MandalaDiaChi>) {
        val json = gson.toJson(diaChiList)
        context.mandalaDiaChiDataStore.edit { preferences ->
            preferences[MANDALA_DIACHI_KEY] = json
        }
    }

    /**
     * Lấy danh sách MandalaDiaChi dưới dạng Flow<List<MandalaDiaChi>>
     */
    fun getMandalaDiaChiList(): Flow<List<MandalaDiaChi>> = context.mandalaDiaChiDataStore.data.map { preferences ->
        val json = preferences[MANDALA_DIACHI_KEY] ?: "[]"
        val type = object : TypeToken<List<MandalaDiaChi>>() {}.type
        gson.fromJson(json, type) ?: emptyList()
    }

    /**
     * Xoá dữ liệu MandalaDiaChi
     */
    suspend fun clearMandalaDiaChiData() {
        context.mandalaDiaChiDataStore.edit { preferences ->
            preferences.remove(MANDALA_DIACHI_KEY)
        }
    }
}
