package app.medapp.data

import app.medapp.data.models.Test

object TestsRepository {
    fun getTestById(testId: Int): Test {
        return when (testId) {
            1 -> TinettiData.tinettiTest
            2 -> GDSData.GDSTest
            3 -> LawtonData.LawtonTest
            4 -> MEEMData.MEEMTest
            5 -> WhisperData.WhisperTest
            6 -> KATZData.KATZTest
            else -> TinettiData.tinettiTest // default fallback
        }
    }
}