package app.medapp.data

import app.medapp.data.models.AnswerOption
import app.medapp.data.models.Question
import app.medapp.data.models.ReferenceMapping
import app.medapp.data.models.Test
import app.medapp.data.models.TestLimits

object GDSData {
    val GDSTest = Test(
        id = 3,
        testName = "Escala de depressão geriátrica GDS 15",
        pacientName = "",
        doctorName = "",
        pacientAge = 0,
        date = "",
        testLimits = TestLimits(
            reference = listOf(
                "Normal: 0 - 5 pontos",
                "Depressão leve: 6 - 10 pontos",
                "Depressão severa: 11 - 15 pontos",
            ),
            resultMappings = listOf(
                ReferenceMapping(
                    minScore = 0,
                    maxScore = 5,
                    message = "Normal"
                ),
                ReferenceMapping(
                    minScore = 6,
                    maxScore = 10,
                    message = "Depressão leve"
                ),
                ReferenceMapping(
                    minScore = 11,
                    maxScore = 15,
                    message = "Depressão severa"
                ),
            )
        ),
        questions = listOf(
            Question(
                id = 1,
                text = "1. Está satisfeito(a) com sua vida?",
                alternatives = listOf(
                    AnswerOption("Não", 0),
                    AnswerOption("Sim", 1)
                )
            ),
            Question(
                id = 2,
                text = "2. Interrompeu muitas de suas atividades?",
                alternatives = listOf(
                    AnswerOption("Não", 0),
                    AnswerOption("Sim", 1)
                )
            ),
            Question(
                id = 3,
                text = "3. Acha sua vida vazia?",
                alternatives = listOf(
                    AnswerOption("Não", 0),
                    AnswerOption("Sim", 1)
                )
            ),
            Question(
                id = 4,
                text = "4. Aborrece-se com frequência?",
                alternatives = listOf(
                    AnswerOption("Não", 0),
                    AnswerOption("Sim", 1)
                )
            ),
            Question(
                id = 5,
                text = "5. Sente-se bem com a vida na maior parte do tempo?",
                alternatives = listOf(
                    AnswerOption("Não", 1),
                    AnswerOption("Sim", 0)
                )
            ),
            Question(
                id = 6,
                text = "6. Teme que algo ruim lhe aconteça?",
                alternatives = listOf(
                    AnswerOption("Não", 0),
                    AnswerOption("Sim", 1)
                )
            ),
            Question(
                id = 7,
                text = "7. Sente-se alegre a maior parte do tempo?",
                alternatives = listOf(
                    AnswerOption("Não", 1),
                    AnswerOption("Sim", 0)
                )
            ),
            Question(
                id = 9,
                text = "8. Sente-se desamparado com frequência?",
                alternatives = listOf(
                    AnswerOption("Não", 0),
                    AnswerOption("Sim", 1)
                )
            ),
            // Section 2: Marcha
            Question(
                id = 10,
                text = "9. Prefere ficar em casa a sair e fazer coisas novas?",
                alternatives = listOf(
                    AnswerOption("Não", 0),
                    AnswerOption("Sim", 1)
                )
            ),
            Question(
                id = 11,
                text = "10. Acha que tem mais problemas de memória que outras pessoas?",
                alternatives = listOf(
                    AnswerOption("Não", 0),
                    AnswerOption("Sim", 1)
                )
            ),
            Question(
                id = 12,
                text = "11. Acha que é maravilhoso estar vivo(a)?",
                alternatives = listOf(
                    AnswerOption("Não", 1),
                    AnswerOption("Sim", 0)
                )
            ),
            Question(
                id = 13,
                text = "12. Sente-se inútil?",
                alternatives = listOf(
                    AnswerOption("Não", 0),
                    AnswerOption("Sim", 1)
                )
            ),
            Question(
                id = 14,
                text = "13. Sente-se cheio(a) de energia?",
                alternatives = listOf(
                    AnswerOption("Não", 1),
                    AnswerOption("Sim", 0)
                )
            ),
            Question(
                id = 15,
                text = "14. Sente-se sem esperança?",
                alternatives = listOf(
                    AnswerOption("Não", 0),
                    AnswerOption("Sim", 1)
                )
            ),
            Question(
                id = 16,
                text = "15. Acha que os outros têm mais sorte que você?",
                alternatives = listOf(
                    AnswerOption("Não", 0),
                    AnswerOption("Sim", 1)
                )
            )
        ))
}