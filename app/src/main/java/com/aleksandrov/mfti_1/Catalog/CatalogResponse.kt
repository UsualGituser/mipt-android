package com.aleksandrov.mfti_1.Catalog

import com.aleksandrov.mfti_1.Catalog.db.RestaurantDao
import com.aleksandrov.mfti_1.Catalog.db.mapToRestaurantEntity
import com.aleksandrov.mfti_1.Catalog.db.mapToRestaurantInfo
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.Serializable
import javax.inject.Inject

@Serializable
data class CatalogResponse(
    val nearest: List<RestaurantInfo>,
    val popular: List<RestaurantInfo>,
    val commercial: CatalogCommercial?
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

class RestaurantRepository @Inject constructor(
    private val httpClient: HttpClient,
    private val catalogDao: RestaurantDao
) {
    suspend fun fetchCatalog(): Flow<CatalogResponse> {

        return flow {
            val cache = catalogDao.getAll()
            if (cache.isNotEmpty()) {
                emit(
                    CatalogResponse(
                        nearest = emptyList(),
                        popular = cache.map { it.mapToRestaurantInfo() },
                        commercial = null
                    )
                )
            }

            try {
                val response = httpClient.request("http://195.2.84.138:8081/catalog") {
                    method = HttpMethod.Get
                }.body<CatalogResponse>()

                catalogDao.insertAll(*response.nearest.map {
                    it.mapToRestaurantEntity()
                }.toTypedArray())

                emit(response)
            } catch (e: Exception) {
                //Error
            }
        }
    }
}

