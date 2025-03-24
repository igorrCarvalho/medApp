package app.medapp.data

data class AnswerOption(
    val text: String,
    val value: Int,
    var isSelected: Boolean = false
)