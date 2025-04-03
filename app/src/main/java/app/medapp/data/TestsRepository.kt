package app.medapp.data

import app.medapp.data.models.Test

object TestsRepository {
    fun getTestById(testId: Int): Test {
        return when (testId) {
            1 -> LawtonData.LawtonTest
            2 -> KATZData.KATZTest
            3 -> GDSData.GDSTest
            4 -> MEEMData.MEEMTest
            5 -> TinettiData.tinettiTest
            6 -> WhisperData.WhisperTest
            else -> TinettiData.tinettiTest // default fallback
        }
    }
}