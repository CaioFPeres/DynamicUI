package com.example.dynamicui.data.repository

import com.example.dynamicui.data.remote.AssistantResponseAPI
import com.example.dynamicui.data.remote.RetrofitClient
import model.AssistantResponse
import repository.AssistantResponseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class AssistantResponseRepositoryImpl(
    private val retrofitClient: RetrofitClient
) : AssistantResponseRepository {
    private val newsApi = retrofitClient.create(AssistantResponseAPI::class.java)

    override suspend fun getAssistantData(): AssistantResponse = withContext(Dispatchers.IO) {
        suspendCoroutine { continuation ->
            val call = newsApi.getAssistantResponse()

            call?.enqueue(object : retrofit2.Callback<AssistantResponse?> {
                override fun onResponse(call: retrofit2.Call<AssistantResponse?>, response: retrofit2.Response<AssistantResponse?>) {
                    if (response.isSuccessful) {
                        continuation.resume(response.body()!!)
                    } else {
                        continuation.resumeWithException(Exception("API error: ${response.code()}")) //Won't crash on error
                    }
                }

                override fun onFailure(call: retrofit2.Call<AssistantResponse?>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })
        }
    }
}