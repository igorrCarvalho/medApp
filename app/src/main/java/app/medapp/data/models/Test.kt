package app.medapp.data.models

data class Test(
    val id: Int,
    val testName: String,
    val pacientName: String,
    val doctorName: String,
    val questions: List<Question>,
    val pacientAge: Int,
    val date: String,
    val testLimits: TestLimits,
    val testDescription: String? = null
)