package com.virastudio.postgeneratorai.models.api

data class ApiRessponse(
    val image_urls: List<String>? = listOf(),
    val message: String ?= ""
)