package com.aleksandrov.mfti_1.Catalog

import android.content.Context
import androidx.room.Room
import com.aleksandrov.mfti_1.Catalog.db.AppDatabase
import com.aleksandrov.mfti_1.Catalog.db.RestaurantDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class CatalogDatabaseModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "food_delivery"
    ).build()

    @Provides
    fun provideRestaurantsDao(appDatabase: AppDatabase): RestaurantDao =
        appDatabase.restaurantDao()
}