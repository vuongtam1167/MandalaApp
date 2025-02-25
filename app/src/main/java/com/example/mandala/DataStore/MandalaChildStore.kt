package com.example.mandala.Datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.mandala.Dataclass.MandalaChild
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.mandalaChildDataStore by preferencesDataStore(name = "mandala_child_store")

class MandalaChildStore(private val context: Context) {

    private val gson = Gson()

    companion object {
        private val MANDALA_CHILD_KEY = stringPreferencesKey("mandala_child_data")
    }

    /**
     * Lưu đối tượng MandalaChild dưới dạng JSON
     */
    suspend fun saveMandalaChild(child: MandalaChild) {
        val json = gson.toJson(child)
        context.mandalaChildDataStore.edit { preferences ->
            preferences[MANDALA_CHILD_KEY] = json
        }
    }

    /**
     * Lấy đối tượng MandalaChild dưới dạng Flow<MandalaChild?>
     */
    fun getMandalaChild(): Flow<MandalaChild?> = context.mandalaChildDataStore.data.map { preferences ->
        val json = preferences[MANDALA_CHILD_KEY] ?: return@map null
        gson.fromJson(json, MandalaChild::class.java)
    }

    /**
     * Xoá dữ liệu MandalaChild
     */
    suspend fun clearMandalaChild() {
        context.mandalaChildDataStore.edit { preferences ->
            preferences.remove(MANDALA_CHILD_KEY)
        }
    }
}
