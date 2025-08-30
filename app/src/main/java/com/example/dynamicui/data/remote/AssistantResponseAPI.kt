package com.example.dynamicui.data.remote

import model.AssistantRequest
import model.AssistantResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import com.example.dynamicui.BuildConfig

// Please replace gradle.properties API_KEY value with your Gemini API Key.
interface AssistantResponseAPI {
    @POST("v1beta/models/gemini-2.0-flash:generateContent")
    fun getAssistantResponse(
        @Body body: AssistantRequest,
        @Header("Content-Type") userAgent: String = "application/json",
        @Header("X-goog-api-key") apiKey: String = BuildConfig.API_KEY
    ): Call<AssistantResponse?>?
}