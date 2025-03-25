package app.medapp.data

object TestsRepository {
    fun getTestById(testId: Int): Test {
        return when (testId) {
            1 -> TinettiData.tinettiTest
            2 -> GDSData.GDSTest
            3 -> LawtonData.LawtonTest
            else -> TinettiData.tinettiTest // default fallback
        }
    }
}