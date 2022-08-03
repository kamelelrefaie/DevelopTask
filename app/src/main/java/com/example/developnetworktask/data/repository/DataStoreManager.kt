package com.example.developnetworktask.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

const val PREFERENCE_NAME = "user"


class DataStoreManager(val context: Context) {

    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCE_NAME)


    companion object {
        val PHONE = stringPreferencesKey("PHONE")
    }

    suspend fun save(phone: String) {
        context.dataStore.edit {
            it[PHONE] = phone
        }
    }


    suspend fun getFromDataStore() = context.dataStore.data.map { it[PHONE] ?: "" }

}