package com.aleksandrov.mfti_1.Catalog.db

import androidx.room.*
import com.aleksandrov.mfti_1.Catalog.RestaurantInfo

@Entity(tableName = "restaurant")
data class RestaurantEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "logo") val logo: String,
    @ColumnInfo(name = "deliveryTime") val time: String
)

fun RestaurantInfo.mapToRestaurantEntity(): RestaurantEntity {
    return RestaurantEntity(id = id, name = name, logo = image, time = deliveryTime)
}

fun RestaurantEntity.mapToRestaurantInfo() : RestaurantInfo {
    return RestaurantInfo(id = id, name = name, image = logo, deliveryTime = time)
}

@Dao
interface RestaurantDao {
    @Query("SELECT * FROM restaurant")
    fun getAll(): List<RestaurantEntity>

    @Insert
    fun insertAll(vararg restaurants: RestaurantEntity)

    @Delete
    fun delete(restaurantEntity: RestaurantEntity)
}

@Database(entities = [RestaurantEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun restaurantDao(): RestaurantDao
}