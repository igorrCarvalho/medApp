package app.medapp.data

// Represents a single answer choice.
data class AnswerOption(
    val text: String,
    val value: Int,
    var isSelected: Boolean = false
)