package com.cursokotlin.myfavoritelist.store

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.cursokotlin.myfavoritelist.model.Item
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

//Inicialización de DataStore
val Context.dataStore by preferencesDataStore(name = "item_favorites")

class FavoritesDataStore(private val context: Context) {

    private val favoritelistitemkey = stringPreferencesKey("favorite_item_list")
    private val gson = Gson()

    //Obtener el flujo de favoritos
    val favoriteItemListFlow: Flow<List<Item>> = context.dataStore.data
        .map { preferences ->
            val jsonString = preferences[favoritelistitemkey] ?: ""
            if(jsonString.isNotEmpty()) {
                // Deserializar la cadena JSON a una lista de objetos Item
                val listType = object : TypeToken<List<Item>>() {}.type
                gson.fromJson(jsonString, listType)
            } else {
                emptyList()
            }
        }


    // Agregar un Item a favoritos
    suspend fun addFavoriteItem(item: Item) {
        context.dataStore.edit { preferences ->
            val currentFavorites = preferences[favoritelistitemkey]?.let { jsonString ->
                val listType = object : TypeToken<List<Item>>() {}.type
                gson.fromJson<List<Item>>(jsonString, listType)
            } ?: emptyList()

            val updatedFavorites = currentFavorites + item
            preferences[favoritelistitemkey] = gson.toJson(updatedFavorites)
        }
    }

    // Eliminar un Item de favoritos
    suspend fun removeFavoriteItem(item: Item) {
        context.dataStore.edit { preferences ->
            val currentFavorites = preferences[favoritelistitemkey]?.let { jsonString ->
                val listType = object : TypeToken<List<Item>>() {}.type
                gson.fromJson<List<Item>>(jsonString, listType)
            } ?: emptyList()

            val updatedFavorites = currentFavorites.filter { it.name != item.name }
            preferences[favoritelistitemkey] = gson.toJson(updatedFavorites)
        }
    }
}





