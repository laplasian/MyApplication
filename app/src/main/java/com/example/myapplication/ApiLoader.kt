package com.example.myapplication

import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class ApiLoader {

    // Http client
    val client = HttpClient(OkHttp.create()) {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

    suspend fun getCharacters(page: Int = 1): List<ItemBase.IconItem> {
        val response = client.get {
            url("https://rickandmortyapi.com/api/character?page=$page")
            contentType(ContentType.Application.Json)
        }.body<ResultResponse<CharacterResponse>>()

        return response.results.map { ch ->
            ItemBase.IconItem(name = ch.name, iconUrl = ch.image, status = ch.status)
        }
    }
}
