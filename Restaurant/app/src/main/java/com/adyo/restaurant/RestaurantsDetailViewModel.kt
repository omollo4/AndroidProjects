package com.adyo.restaurant

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RestaurantsDetailViewModel: ViewModel() {
    private var restInterface: RestaurantApiService
    val state = mutableStateOf<Restaurant?>(null)

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://restaurants-db-default-rtdb.firebaseio.com/")
            .build()
        restInterface = retrofit.create(RestaurantApiService::class.java)

        //val id = stateHandle.get<Int>("restaurant_id") ?: 0
        viewModelScope.launch {
            val restaurant = getRemoteRestaurant(2)
            state.value = restaurant
        }
    }

    private suspend fun getRemoteRestaurant(id: Int): Restaurant {
        return withContext(Dispatchers.IO) {
            val response =  restInterface.getRestaurant(id)
            return@withContext response.values.first()
        }
    }
}