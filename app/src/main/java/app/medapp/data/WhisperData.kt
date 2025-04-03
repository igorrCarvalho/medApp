package app.medapp.data

import app.medapp.data.models.AnswerOption
import app.medapp.data.models.Question
import app.medapp.data.models.Test
import app.medapp.data.models.TestLimits
import app.medapp.R
import app.medapp.data.models.ReferenceMapping

object WhisperData {
    val WhisperTest = Test(
        id = 6,
        testName = "Teste do Sussurro (avaliação da audição)",
        pacientName = "",
        doctorName = "",
        pacientAge = 0,
        date = "",
        testLimits = TestLimits(
            reference = listOf(
                "Teste negativo: 1 ponto",
                "Teste positivo para diminuição da acuidade auditiva: 0 pontos"
            ),
            resultMappings = listOf(
                ReferenceMapping(
                    minScore = 0,
                    maxScore = 0,
                    message = "Teste positivo para diminuição da acuidade auditiva"
                ),
                ReferenceMapping(
                    minScore = 1,
                    maxScore = 1,
                    message = "Teste negativo"
                ),
            )
        ),
        questions = listOf(
            Question(
                id = 1,
                text = "1. O avaliador deve ficar fora do campo visual da pessoa idosa, a uma distância de aproximadamente 33cm e sussurrar uma frase em cada ouvido do paciente.",
                alternatives = listOf(
                    AnswerOption("Paciente não respondeu/ouviu", 0),
                    AnswerOption("Paciente respondeu/ouviu", 1)
                )
            )
        ))
}