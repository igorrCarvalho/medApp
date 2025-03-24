package app.medapp.data

data class Question(
    val id: Int,
    val text: String,
    val alternatives: List<AnswerOption>,
    val subQuestions: List<Question>? = null
)