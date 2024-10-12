package com.cursokotlin.myfavoritelist.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.cursokotlin.myfavoritelist.model.Item
import com.cursokotlin.myfavoritelist.store.FavoritesDataStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class FavoritesViewModel(application: Application) : AndroidViewModel(application){

    private val favoriteDataStore = FavoritesDataStore(application)

    private val _favoriteItemList = MutableStateFlow<List<Item>>(emptyList())
    val favoriteItemList: StateFlow<List<Item>> = _favoriteItemList

    init {
       loadFavoriteItemList()
    }

    //Cargar la lista de Item favoritos desde DataStore
    private fun loadFavoriteItemList() {
       viewModelScope.launch {
           favoriteDataStore.favoriteItemListFlow.collect { list ->
              _favoriteItemList.value = list
           }
       }
    }


    //Agregar un Item a favoritos
    fun addFavoriteItem(item:Item){
        viewModelScope.launch {
            favoriteDataStore.addFavoriteItem(item)
        }
    }

    //Elimina un Item a favoritos
    fun removeFavoriteItem(item: Item){
        viewModelScope.launch {
            favoriteDataStore.removeFavoriteItem(item)
        }
    }


}