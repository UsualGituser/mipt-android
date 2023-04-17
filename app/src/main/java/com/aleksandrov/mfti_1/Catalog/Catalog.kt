package com.aleksandrov.mfti_1.Catalog

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.aleksandrov.mfti_1.R




@Composable
fun FoodCatalog(catalogViewModel: CatalogViewModel = viewModel()) {
    val state by catalogViewModel.viewState.observeAsState()
    val catalogViewState = state ?: return

    LazyColumn(content = {
        catalogViewState.nearestRestaurant.forEach {
            item{
                RestaurantCard(icon = it.logo, name = it.name, deliveryTime = it.deliveryTime)
            }
        }

        catalogViewState.popularRestaurant.forEach {
            item{
                RestaurantCard(icon = it.logo, name = it.name, deliveryTime = it.deliveryTime)
            }
        }
    },
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    )
}

@Composable
fun RestaurantCard(icon: String, name: String, deliveryTime: String) {
    Card(modifier=Modifier.fillMaxWidth()) {
        Row() {
            Image(
                painter = rememberAsyncImagePainter(icon),
                contentDescription = name,
                modifier = Modifier.size(128.dp)
            )
            Text(modifier = Modifier.padding(horizontal = 5.dp), text = name)
            Text(modifier = Modifier.padding(horizontal = 5.dp), text = deliveryTime)
        }
    }
}
