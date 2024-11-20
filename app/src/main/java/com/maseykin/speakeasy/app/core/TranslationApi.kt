package com.maseykin.speakeasy.app.core

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TranslationApi {

    @GET("translate")
    suspend fun translate(
        @Query("sl") sourceLanguage: String,
        @Query("dl") destinationLanguage: String,
        @Query("text") text: String
    ): TranslationResponse

    @GET("languages")
    suspend fun getLanguages(): Map<String, String>

    @Serializable
    data class Language(
        val code: String,
        val name: String
    )

    @Serializable
    data class TranslationResponse(
        val pronunciation: Pronunciation,
        val translations: Translations,
    )

    @Serializable
    data class Pronunciation(
        @SerialName("source-text-audio") val sourceTextAudio: String?,
        @SerialName("destination-text-audio") val destinationTextAudio: String?,
    )

    @Serializable
    data class Translations(
        @SerialName("possible-translations") val possibleTranslations: List<String>,
    )
}