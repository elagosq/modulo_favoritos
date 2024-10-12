package com.cursokotlin.myfavoritelist.views

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.cursokotlin.myfavoritelist.components.ItemRow
import com.cursokotlin.myfavoritelist.components.MainTopBar
import com.cursokotlin.myfavoritelist.model.Item
import com.cursokotlin.myfavoritelist.viewModel.FavoritesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FavoritesScreen(navController: NavController, viewModel: FavoritesViewModel = viewModel()) {
    val favoriteItemList by viewModel.favoriteItemList.collectAsState()
    println(favoriteItemList)

    Scaffold(
        topBar = {
            MainTopBar(
                title = "Listado Favoritos",
                showBackButton = true
            ) { navController.popBackStack() }
        }
    ) {
        ContentFavoriteView(it, viewModel, favoriteItemList)
    }
}

@Composable
fun ContentFavoriteView(
    pad: PaddingValues,
    viewModel: FavoritesViewModel,
    favoriteItemList: List<Item>
) {
    Column(
        modifier = Modifier
            .padding(pad)
    ) {
        if (favoriteItemList.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(favoriteItemList.size) { index ->
                    val item = favoriteItemList[index]
                    ItemRow(item, viewModel, favoriteItemList)
                }
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("No hay Item en favoritos", style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}