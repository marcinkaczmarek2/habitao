package app.habitao.ui.components.stats

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// jeden wspólny DataStore dla flagi startu
private val Context.appLaunchDataStore by preferencesDataStore("app_launch_prefs")

object AppLaunchDataStore {

    private val KEY_FIRST_LAUNCH = booleanPreferencesKey("first_launch")

    // Flow, które mówi czy to pierwsze uruchomienie
    fun isFirstLaunch(context: Context): Flow<Boolean> =
        context.appLaunchDataStore.data.map { prefs ->
            prefs[KEY_FIRST_LAUNCH] ?: true   // domyślnie true
        }

    // ustawienie flagi
    suspend fun setFirstLaunch(context: Context, value: Boolean) {
        context.appLaunchDataStore.edit { prefs ->
            prefs[KEY_FIRST_LAUNCH] = value
        }
    }
}
