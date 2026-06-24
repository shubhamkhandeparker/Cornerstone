package com.shubham.cornerstone

import retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

/**
 * The Groq API endpoint. Retrofit turns this interface into real network calls.
 */
interface GroqApi {
    @POST("openai/v1/chat/completions")
    suspend fun chat(
        @Header("Authorization") authorization: String,
        @Body request: GroqRequest
    ): GroqResponse
}

/**
 * Builds and holds the single Groq API client.
 */
object GroqClient {

    private const val BASE_URL = "https://api.groq.com/"

    // Lenient JSON: ignores unexpected fields Groq might add.
    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttp = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    val api: GroqApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttp)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(GroqApi::class.java)
    }
}