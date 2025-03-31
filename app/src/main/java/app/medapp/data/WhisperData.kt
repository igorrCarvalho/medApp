package app.medapp.data

import app.medapp.data.models.AnswerOption
import app.medapp.data.models.Question
import app.medapp.data.models.Test
import app.medapp.data.models.TestLimits
import app.medapp.R

object WhisperData {
    val WhisperTest = Test(
        id = 5,
        testName = "Teste do Sussurro (avaliação da audição)",
        pacientName = "",
        doctorName = "",
        pacientAge = 0,
        date = "",
        testLimits = TestLimits(
            reference = listOf("Normal: acima de 27 pontos", "Demência: menor ou igual a 24 pontos; em caso de menos de 4 anos de escolaridade, o ponto de corte passa para 17, em vez de 24."),
            cutNumber = 24,
            greatMsg = "Quadro normal",
            mediumMsg = "Possível caso de demência",
            highMsg = "Possibilidade alta de demência"
        ),
        questions = listOf(
            Question(
                id = 1,
                text = "1. Compreende a fala em situações sociais?",
                alternatives = listOf(
                    AnswerOption("Paciente não respondeu", 0),
                    AnswerOption("Paciente respondeu", 1)
                )
            ),
            Question(
                id = 2,
                text = "2. Tem necessidade que as pessoas repitam o que lhe é falado?",
                alternatives = listOf(
                    AnswerOption("Paciente não respondeu", 0),
                    AnswerOption("Paciente respondeu", 1)
                )
            ),
            Question(
                id = 3,
                text = "3. Sente zumbido ou algum tipo de barulho no ouvido ou cabeça?",
                alternatives = listOf(
                    AnswerOption("Paciente não respondeu", 0),
                    AnswerOption("Paciente respondeu", 1)
                )
            ),
            Question(
                id = 4,
                text = "4. Fala alto demais?",
                alternatives = listOf(
                    AnswerOption("Paciente não respondeu", 0),
                    AnswerOption("Paciente respondeu", 1)
                )
            ),
            Question(
                id = 5,
                text = "5. Evita conversar? Prefere ficar só?",
                alternatives = listOf(
                    AnswerOption("Paciente não respondeu", 0),
                    AnswerOption("Paciente respondeu", 1)
                )
            )
        ))
}