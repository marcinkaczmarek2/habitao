package app.habitao.ui.components

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.themeDataStore by preferencesDataStore(name = "user_theme")

object ThemeDataStore {
    private val KEY_IS_DARK = booleanPreferencesKey("is_dark_mode")

    suspend fun saveTheme(context: Context, isDark: Boolean) {
        context.themeDataStore.edit { prefs ->
            prefs[KEY_IS_DARK] = isDark
        }
    }

    fun getTheme(context: Context): Flow<Boolean> =
        context.themeDataStore.data.map { prefs ->
            prefs[KEY_IS_DARK] ?: true
        }
}
