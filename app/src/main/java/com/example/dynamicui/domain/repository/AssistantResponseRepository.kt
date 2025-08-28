package repository

import model.AssistantResponse

interface AssistantResponseRepository {
    suspend fun getAssistantData(msg: String): AssistantResponse
}