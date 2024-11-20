package com.maseykin.speakeasy.app.core.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [TranslationHistory::class, TranslationFavorites::class], version = 2)
abstract class AppDatabase: RoomDatabase() {

    abstract fun translationHistoryDao(): TranslationHistoryDao
    abstract fun translationsFavoritesDao(): TranslationFavoritesDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).addMigrations(MIGRATION_1_2).build()
                INSTANCE = instance
                instance
            }
        }

        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("""
            CREATE TABLE IF NOT EXISTS `translation_favorites` (
                `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                `sourceText` TEXT NOT NULL,
                `translatedText` TEXT NOT NULL,
                `timestamp` INTEGER NOT NULL
            )
        """)
            }
        }
    }
}