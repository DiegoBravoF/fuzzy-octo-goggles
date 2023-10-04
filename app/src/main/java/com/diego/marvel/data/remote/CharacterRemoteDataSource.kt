package com.diego.marvel.data.remote

import com.diego.marvel.BuildConfig
import com.diego.marvel.data.api.MarvelService
import com.diego.marvel.data.api.toDomain
import com.diego.marvel.domain.model.CharacterModel
import java.math.BigInteger
import java.security.MessageDigest
import javax.inject.Inject

interface CharacterRemoteDataSource {
    suspend fun getAllCharacters(): Result<List<CharacterModel>>
}

class CharacterRemoteDataSourceImpl @Inject
constructor(private val apiService: MarvelService) : CharacterRemoteDataSource {
    override suspend fun getAllCharacters(): Result<List<CharacterModel>> {
        val response = apiService.getCharacters(timestamp, getHash(timestamp))
        if (response.isSuccessful) {
            response.body()?.let {
                return Result.success(it.data.results.toDomain())
            }
        }
        return Result.failure(exception = Throwable(response.errorBody().toString()))

    }

    val timestamp: String
        get() = System.currentTimeMillis().toString()


    fun getHash(timestamp: String): String {
        val input =
            timestamp + BuildConfig.APIKEY_PRIVATE + BuildConfig.APIKEY_PUBLIC
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
    }
}