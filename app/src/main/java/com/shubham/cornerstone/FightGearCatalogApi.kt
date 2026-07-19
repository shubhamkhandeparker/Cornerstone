package com.shubham.cornerstone

import retrofit2.http.GET
import retrofit2.http.Url

interface FightGearCatalogApi {

    @GET
    suspend fun getCatalog(
        @Url catalogUrl: String
    ): FightGearCatalogResponse
}