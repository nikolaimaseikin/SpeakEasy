package com.maseykin.speakeasy.app.core.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "translation_history")
data class TranslationHistory(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val sourceText: String,
    val translatedText: String,
    val timestamp: Long = Date().time
)