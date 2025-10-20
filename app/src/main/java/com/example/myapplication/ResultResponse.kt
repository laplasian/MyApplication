package com.example.myapplication

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ResultResponse<T>(
    @SerialName("results") val results: List<T>,
)

@Serializable
class CharacterResponse(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("image") val image: String
)