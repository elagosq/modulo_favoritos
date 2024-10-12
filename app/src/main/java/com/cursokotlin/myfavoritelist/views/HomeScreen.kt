package com.cursokotlin.myfavoritelist.views

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.cursokotlin.myfavoritelist.components.ItemRow
import com.cursokotlin.myfavoritelist.components.MainTopBar
import com.cursokotlin.myfavoritelist.model.Item
import com.cursokotlin.myfavoritelist.viewModel.FavoritesViewModel

// Lista de elementos de ejemplo
val itemsList = listOf(
    Item("Item 1", "Descripción 1"),
    Item("Item 2", "Descripción 2"),
    Item("Item 3", "Descripción 3"),
    Item("Item 4", "Descripción 4"),
    Item("Item 5", "Descripción 5"),
    Item("Item 6", "Descripción 6"),
    Item("Item 7", "Descripción 7"),
    Item("Item 8", "Descripción 8"),
    Item("Item 9", "Descripción 9")
)

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController, viewModel: FavoritesViewModel = viewModel()) {
    //val itemList by viewModel.itemList.collectAsState()
    val favoriteItemList by viewModel.favoriteItemList.collectAsState()

    Scaffold(
        topBar = {
           MainTopBar(title = "Listado de Items", onClickBackButton = {})
        },
        floatingActionButton = {
            FloatingActionButton(
                contentColor = Color.White,
                containerColor = Color.Red,
                onClick = { navController.navigate("FavoritesScreen") },
                shape = CircleShape
            ) {
                Icon(imageVector = Icons.Default.Favorite, "Ir a la pantalla favorito")
            }
        }
    ) {
        ContentHomeView(it, viewModel, favoriteItemList)
    }
}

@Composable
fun ContentHomeView(
    pad: PaddingValues,
    viewModel: FavoritesViewModel,
    favoriteItemList: List<Item>
) {
    Column(
        modifier = Modifier
            .padding(pad)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            items(itemsList.size) { index ->
                val item = itemsList[index]
                ItemRow(item, viewModel, favoriteItemList)
            }
        }
    }
}







