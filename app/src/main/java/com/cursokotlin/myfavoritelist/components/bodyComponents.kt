package com.cursokotlin.myfavoritelist.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cursokotlin.myfavoritelist.model.Item
import com.cursokotlin.myfavoritelist.viewModel.FavoritesViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(
    title: String,
    showBackButton: Boolean = false,
    onClickBackButton: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                color = Color.Black,
                fontWeight = FontWeight.ExtraBold
            )
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color.White
        ),
        navigationIcon = {
            if (showBackButton) {
                IconButton(onClick = { onClickBackButton() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = "",
                        tint = Color.Black
                    )
                }
            }
        }
    )
}

@Composable
fun ItemRow(item: Item, viewModel: FavoritesViewModel, favoriteItemList: List<Item>) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(horizontal = 20.dp, vertical = 10.dp)
            .shadow(
                elevation = 10.dp,
                shape = RoundedCornerShape(10.dp)
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Text(text = item.name)
            Text(text = item.description)
            IconButton(onClick = {
                if (favoriteItemList.contains(item)) {
                    viewModel.removeFavoriteItem(item)
                } else {
                    viewModel.addFavoriteItem(item)
                }
            }) {
                Icon(
                    imageVector = if (favoriteItemList.contains(item)) {
                        Icons.Default.Favorite
                    } else {
                        Icons.Default.FavoriteBorder
                    }, contentDescription = null
                )
            }
        }
    }
}