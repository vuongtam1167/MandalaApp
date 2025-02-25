package com.example.mandala.Datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.mandala.Dataclass.MandalaItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.mandalaItemDataStore by preferencesDataStore(name = "mandala_item_store")

class MandalaItemStore(private val context: Context) {

    private val gson = Gson()

    companion object {
        private val MANDALA_ITEM_KEY = stringPreferencesKey("mandala_item_data")
    }

    /**
     * Lưu danh sách MandalaItem dưới dạng JSON
     */
    suspend fun saveMandalaItemList(itemList: List<MandalaItem>) {
        val json = gson.toJson(itemList)
        context.mandalaItemDataStore.edit { preferences ->
            preferences[MANDALA_ITEM_KEY] = json
        }
    }

    /**
     * Lấy danh sách MandalaItem dưới dạng Flow<List<MandalaItem>>
     */
    fun getMandalaItemList(): Flow<List<MandalaItem>> = context.mandalaItemDataStore.data.map { preferences ->
        val json = preferences[MANDALA_ITEM_KEY] ?: "[]"
        val type = object : TypeToken<List<MandalaItem>>() {}.type
        gson.fromJson(json, type) ?: emptyList()
    }

    /**
     * Xoá dữ liệu MandalaItem
     */
    suspend fun clearMandalaItemData() {
        context.mandalaItemDataStore.edit { preferences ->
            preferences.remove(MANDALA_ITEM_KEY)
        }
    }
}
