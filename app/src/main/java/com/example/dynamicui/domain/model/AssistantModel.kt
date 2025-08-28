package model

data class AssistantRequest(
    val contents: List<Content>
)

data class AssistantResponse(
    val candidates: List<Candidate>
)

data class Candidate(
    val content: Content
)

data class Content(
    val parts: List<Part>
)

data class Part(
    val text: String
)