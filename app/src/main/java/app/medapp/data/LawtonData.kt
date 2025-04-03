package app.medapp.data

import app.medapp.data.models.AnswerOption
import app.medapp.data.models.Question
import app.medapp.data.models.ReferenceMapping
import app.medapp.data.models.Test
import app.medapp.data.models.TestLimits

object LawtonData {
    val LawtonTest = Test(
        id = 1,
        testName = "Escala de avaliação das AIVD de Lawton",
        pacientName = "",
        doctorName = "",
        pacientAge = 0,
        date = "",
        testLimits = TestLimits(
            reference = listOf(
                "Totalmente dependente: 0 - 9 pontos",
                "Dependência grave: 10 - 15 pontos",
                "Dependência moderada: 16 - 20 pontos",
                "Dependência leve: 21 - 25 pontos",
                "Independente: 26 - 27 pontos",
            ),
            resultMappings = listOf(
                ReferenceMapping(
                    minScore = 0,
                    maxScore = 9,
                    message = "Totalmente dependente"
                ),
                ReferenceMapping(
                    minScore = 10,
                    maxScore = 15,
                    message = "Dependência grave"
                ),
                ReferenceMapping(
                    minScore = 16,
                    maxScore = 20,
                    message = "Dependência moderada"
                ),
                ReferenceMapping(
                    minScore = 21,
                    maxScore = 25,
                    message = "Dependência leve"
                ),
                ReferenceMapping(
                    minScore = 26,
                    maxScore = 27,
                    message = "Independente"
                ),
            )
        ),
        questions = listOf(
            Question(
                id = 1,
                text = "1. O(a) Sr(a) consegue usar o telefone?",
                alternatives = listOf(
                    AnswerOption("Sem ajuda", 3),
                    AnswerOption("Com ajuda parcial", 2),
                    AnswerOption("Não consegue", 1)
                )
            ),
            Question(
                id = 2,
                text = "2. O(a) Sr(a) consegue ir a locais distantes, usando algum transporte,\n" +
                        "sem necessidade de planejamentos especiais?",
                alternatives = listOf(
                    AnswerOption("Sem ajuda", 3),
                    AnswerOption("Com ajuda parcial", 2),
                    AnswerOption("Não consegue", 1)
                )
            ),
            Question(
                id = 3,
                text = "3. O(a) Sr(a) consegue fazer compras?",
                alternatives = listOf(
                    AnswerOption("Sem ajuda", 3),
                    AnswerOption("Com ajuda parcial", 2),
                    AnswerOption("Não consegue", 1)
                )
            ),
            Question(
                id = 4,
                text = "4. O(a) Sr(a) consegue preparar suas próprias refeições?",
                alternatives = listOf(
                    AnswerOption("Sem ajuda", 3),
                    AnswerOption("Com ajuda parcial", 2),
                    AnswerOption("Não consegue", 1)
                )
            ),
            Question(
                id = 5,
                text = "5. O(a) Sr(a) consegue arrumar a casa?",
                alternatives = listOf(
                    AnswerOption("Sem ajuda", 3),
                    AnswerOption("Com ajuda parcial", 2),
                    AnswerOption("Não consegue", 1)
                )
            ),
            Question(
                id = 6,
                text = "6. O(a) Sr(a) consegue fazer trabalhos manuais domésticos, como pequenos reparos?",
                alternatives = listOf(
                    AnswerOption("Sem ajuda", 3),
                    AnswerOption("Com ajuda parcial", 2),
                    AnswerOption("Não consegue", 1)
                )
            ),
            Question(
                id = 7,
                text = "7. O(a) Sr(a) consegue lavar e passar sua roupa?",
                alternatives = listOf(
                    AnswerOption("Sem ajuda", 3),
                    AnswerOption("Com ajuda parcial", 2),
                    AnswerOption("Não consegue", 1)
                )
            ),
            Question(
                id = 8,
                text = "8. O(a) Sr(a) consegue tomar seus remédios na dose e horários corretos?",
                alternatives = listOf(
                    AnswerOption("Sem ajuda", 3),
                    AnswerOption("Com ajuda parcial", 2),
                    AnswerOption("Não consegue", 1)
                )
            ),
            Question(
                id = 9,
                text = "9. O(a) Sr(a) consegue cuidar de suas finanças?",
                alternatives = listOf(
                    AnswerOption("Sem ajuda", 3),
                    AnswerOption("Com ajuda parcial", 2),
                    AnswerOption("Não consegue", 1)
                )
            )
        ))
}