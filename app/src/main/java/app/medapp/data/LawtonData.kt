package app.medapp.data

object LawtonData {
    val LawtonTest = Test(
        id = 3,
        testName = "AVALIAÇÃO DAS ATIVIDADES INSTRUMENTAIS DE VIDA DIÁRIA (AIVD)",
        pacientName = "",
        doctorName = "",
        pacientAge = 0,
        date = "",
        testLimits = TestLimits(
            reference = "Quanto maior o escore melhor",
            cutNumber = 13,
            greatMsg = "Desempenho Ótimo",
            mediumMsg = "Desempenho Médio",
            highMsg = "Desempenho ruim"
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