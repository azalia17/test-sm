package com.azalia.suitmediatestkm

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreference (private val dataStore: DataStore<Preferences>) {
    fun getUser(): Flow<String?> = dataStore.data.map { it[USER_KEY] }

    suspend fun saveUser(user: String) {
        dataStore.edit { preferences ->
            preferences[USER_KEY] = user
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: UserPreference? = null

        private val USER_KEY = stringPreferencesKey("user")

        fun getInstance(dataStore: DataStore<androidx.datastore.preferences.core.Preferences>): UserPreference {
            return INSTANCE ?: synchronized(this) {
                val instance = UserPreference(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }

}