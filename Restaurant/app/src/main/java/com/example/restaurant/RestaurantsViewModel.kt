package com.example.restaurant

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RestaurantsViewModel(private val stateHandle: SavedStateHandle): ViewModel(){
    val state = mutableStateOf(emptyList<Restaurant>())
    private val restInterface: RestaurantApiService

    private lateinit var restaurantsCall: Call<List<Restaurant>>

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://restaurants-1c60d-default-rtdb.firebaseio.com/")
            .build()
        restInterface = retrofit.create(RestaurantApiService::class.java)
        getRestaurants()
    }
   private fun getRestaurants(){
        restaurantsCall = restInterface.getRestaurants()
        restaurantsCall.enqueue(
            object: Callback<List<Restaurant>> {
                override fun onResponse(
                    call: Call<List<Restaurant>>,
                    response: Response<List<Restaurant>>
                ) {
                    response.body()?.let {restaurants ->
                        state.value = restaurants.restoreSelection()
                }
            }
                override fun onFailure(call: Call<List<Restaurant>>, t: Throwable) {
                    t.printStackTrace()
                }
            }
        )
    }


    fun toggleFavorite(id: Int){
        val restaurants = state.value.toMutableList()
        val itemIndex = restaurants.indexOfFirst { it.id == id }
        val item = restaurants[itemIndex]
        restaurants[itemIndex] = item.copy(isFavorite = !item.isFavorite)
        storeSelection(restaurants[itemIndex])
        state.value = restaurants
    }

    private fun storeSelection(item: Restaurant) {
        val savedToggled = stateHandle.get<List<Int>?>(FAVORITES)
            .orEmpty().toMutableList()
        if (item.isFavorite) savedToggled.add(item.id)
        else savedToggled.remove(item.id)
        stateHandle[FAVORITES] = savedToggled
    }

    private fun List<Restaurant>.restoreSelection(): List<Restaurant> {
            stateHandle.get<List<Int>?>(FAVORITES)?.let {
                    selectedIds ->
                val restaurantsMap = this.associateBy { it.id }
                selectedIds.forEach { id ->
                    restaurantsMap[id]?.isFavorite = true
                }
                return restaurantsMap.values.toList()
            }
            return this
    }
    companion object {
        const val FAVORITES = "favorites"
    }

    override fun onCleared() {
        super.onCleared()
        restaurantsCall.cancel()
    }
}