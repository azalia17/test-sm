package com.azalia.suitmediatestkm

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.azalia.suitmediatestkm.data.Repository

object Injection {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    fun provideRepository(context: Context): Repository {
        val preference = UserPreference(context.dataStore)
        return Repository(preference)
    }
}