package app.medapp.data.models

data class AnswerOption(
    val text: String,
    val value: Int,
    var isSelected: Boolean = false
)