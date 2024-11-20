package com.maseykin.speakeasy.app.core.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TranslationFavoritesDao {
    @Insert
    suspend fun insertFavorites(favorites: TranslationFavorites)

    @Query("SELECT * FROM translation_favorites ORDER BY timestamp")
    fun getTranslationFavorites(): Flow<List<TranslationFavorites>>
}