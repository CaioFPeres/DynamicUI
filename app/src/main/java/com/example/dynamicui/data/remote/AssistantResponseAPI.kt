package com.example.dynamicui.data.remote

import model.AssistantResponse
import retrofit2.Call
import retrofit2.http.Header
import retrofit2.http.POST

// This is using the apiKey directly into the project code,
// which shouldn't be used in a development environment,
// specially when upload the code to a public repository.
// Instead, one should use BuildConfig by setting a API variable at gradle.properties.
// But I'm doing this way otherwise it would not run seemlessly (with one click).
// getNews parameter should have: @Query("apiKey") apiKey: String = BuildConfig.API_KEY
interface AssistantResponseAPI {
    @POST
    fun getAssistantResponse(
        @Header("Content-Type") userAgent: String = "application/json",
        @Header("Authorization") apiKey: String = "Bearer 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"
    ): Call<AssistantResponse?>?
}