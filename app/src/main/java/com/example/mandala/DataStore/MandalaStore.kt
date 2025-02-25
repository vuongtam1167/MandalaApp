package com.example.mandala.Datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.mandala.Dataclass.Mandala
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Extension property cho DataStore của Context
private val Context.mandalaDataStore by preferencesDataStore(name = "mandala_store")

class MandalaStore(private val context: Context) {

    private val gson = Gson()

    companion object {
        private val MANDALA_KEY = stringPreferencesKey("mandala_data")
    }

    /**
     * Lưu danh sách Mandala dưới dạng JSON
     */
    suspend fun saveMandalaList(mandalaList: List<Mandala>) {
        val json = gson.toJson(mandalaList)
        context.mandalaDataStore.edit { preferences ->
            preferences[MANDALA_KEY] = json
        }
    }

    /**
     * Lấy danh sách Mandala dưới dạng Flow<List<Mandala>>
     */
    fun getMandalaList(): Flow<List<Mandala>> = context.mandalaDataStore.data.map { preferences ->
        val json = preferences[MANDALA_KEY] ?: "[]"
        val type = object : TypeToken<List<Mandala>>() {}.type
        gson.fromJson(json, type) ?: emptyList()
    }

    /**
     * Xoá dữ liệu Mandala
     */
    suspend fun clearMandalaData() {
        context.mandalaDataStore.edit { preferences ->
            preferences.remove(MANDALA_KEY)
        }
    }
}
