package com.diego.marvel.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import java.math.BigInteger
import java.security.MessageDigest

interface MarvelService {

    @GET("/v1/public/characters")
    suspend fun getCharacters(
        @Query("ts") ts: String,
        @Query("hash") hash: String,
        @Query("apikey") apikey: String = "cdbcc3dafe45da31be5e62244edf3517",

    ): Response<CharacterDataWrapper>

}