package com.shubham.cornerstone

import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

object FightGearApiFactory {

    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
        explicitNulls = false
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            // The API uses a complete dynamic HTTPS URL through @Url.
            // Retrofit still requires a valid base URL during creation.
            .baseUrl("https://example.com/")
            .addConverterFactory(
                json.asConverterFactory(
                    "application/json".toMediaType()
                )
            )
            .build()
    }

    val api: FightGearCatalogApi by lazy {
        retrofit.create(FightGearCatalogApi::class.java)
    }
}