package com.diego.marvel.data.remote

import com.diego.marvel.data.api.MarvelService
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection

class CharacterRemoteDataSourceTest {

    private var mockWebServer = MockWebServer()

    private lateinit var apiService: MarvelService

    private lateinit var sut: CharacterRemoteDataSource

    @Before
    fun setUp() {
        mockWebServer.start()
        apiService = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(mockWebServer.url("/")) // note the URL is different from production one
            .build()
            .create(MarvelService::class.java)
        sut = CharacterRemoteDataSourceImpl(
            apiService = apiService
        )
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `when response is ko Result is Faiulure`() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_NOT_FOUND)
        )
        val response = sut.getAllCharacters()
        Assert.assertTrue(response.isFailure)
    }

    @Test
    fun `when response is ok Result is Success`() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(
                    """{
"data": {
"results": [
{
"id": 1011334,
"name": "3-D Man",
"description": "",
"modified": "2014-04-29T14:18:17-0400",
"thumbnail": {
"path": "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784",
"extension": "jpg"
}
},
{
"id": 1017100,
"name": "A-Bomb (HAS)",
"description": "Rick Jones has been Hulk's best bud since day one, but now he's more than a friend...he's a teammate! Transformed by a Gamma energy explosion, A-Bomb's thick, armored skin is just as strong and powerful as it is blue. And when he curls into action, he uses it like a giant bowling ball of destruction! ",
"thumbnail": {
"path": "http://i.annihil.us/u/prod/marvel/i/mg/3/20/5232158de5b16",
"extension": "jpg"
}
}
]
}
}"""
                )
        )
        val response = sut.getAllCharacters()
        Assert.assertTrue(response.isSuccess)
    }
}