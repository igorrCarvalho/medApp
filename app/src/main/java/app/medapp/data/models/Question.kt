package app.medapp.data.models

data class Question(
    val id: Int,
    val text: String,
    val alternatives: List<AnswerOption>,
    val subQuestions: List<Question>? = null,
    val imageResId: Int? = null
)