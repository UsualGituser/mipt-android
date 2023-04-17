package com.aleksandrov.mfti_1.Catalog

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.Serializable
import javax.inject.Inject

@Serializable
data class CatalogResponse(
    val nearest: List<RestaurantInfo>,
    val popular: List<RestaurantInfo>,
    val commercial: CatalogCommercial
)

@Serializable
data class RestaurantInfo(
    val id: Int,
    val name: String,
    val deliveryTime: String,
    val image: String
)

@Serializable
data class CatalogCommercial(
    val picture: String,
    val url: String
)

class RestaurantRepository @Inject constructor(private val httpClient: HttpClient) {
    suspend fun fetchCatalog(): CatalogResponse {
        val response = httpClient.request("http://195.2.84.138:8081/catalog") {
            method = HttpMethod.Get
        }.body<CatalogResponse>()

        return response
    }
}

