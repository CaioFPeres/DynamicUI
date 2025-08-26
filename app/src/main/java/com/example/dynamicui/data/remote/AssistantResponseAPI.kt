package com.example.dynamicui.data.remote

import model.AssistantRequest
import model.AssistantResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

// This is using the apiKey directly into the project code,
// which shouldn't be used in a development environment,
// specially when upload the code to a public repository.
// Instead, one should use BuildConfig by setting a API variable at gradle.properties.
// But I'm doing this way otherwise it would not run seemlessly (with one click).
// getNews parameter should have: @Query("apiKey") apiKey: String = BuildConfig.API_KEY
interface AssistantResponseAPI {
    @POST("v1beta/models/gemini-2.0-flash:generateContent")
    fun getAssistantResponse(
        @Body body: AssistantRequest,
        @Header("Content-Type") userAgent: String = "application/json",
        @Header("X-goog-api-key") apiKey: String = "AIzaSyCQE12of-QMY_kzrlCRMxQzv-0-JB4DORY"
    ): Call<AssistantResponse?>?
}