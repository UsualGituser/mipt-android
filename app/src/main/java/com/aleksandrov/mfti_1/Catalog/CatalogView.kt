package com.aleksandrov.mfti_1.Catalog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aleksandrov.mfti_1.SignInViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class Restaurant(
    val name: String,
    val logo: String,
    val deliveryTime: String
)

fun RestaurantInfo.mapToRestaurant(): Restaurant {
    return Restaurant(name = name, deliveryTime = deliveryTime, logo = image)
}

data class CatalogViewState(
    val nearestRestaurant: List<Restaurant> = emptyList(),
    val popularRestaurant: List<Restaurant> = emptyList(),
    val searchQuery: String = ""
)

@HiltViewModel
class CatalogViewModel @Inject constructor(private val restaurantRepository: RestaurantRepository): ViewModel() {
    private val _viewState =  MutableLiveData(CatalogViewState())
    val viewState: LiveData<CatalogViewState> = _viewState

    private fun fetchRestaurants() {
        viewModelScope.launch {
            val response = restaurantRepository.fetchCatalog()

            _viewState.postValue(
                _viewState.value?.copy(
                    nearestRestaurant = response.nearest.map { it.mapToRestaurant() },
                    popularRestaurant = response.popular.map { it.mapToRestaurant() }
                )
            )
        }
    }

    init {
        fetchRestaurants()
    }

    fun searchQuery(query: String) {
        _viewState.postValue(_viewState.value?.copy(searchQuery = query))
    }

}