package app.medapp.data

object TinettiData {
    val tinettiTest = Test(
        id = 1,
        testName = "Escala de Avaliação do Equilíbrio e da Marcha de Tinetti",
        pacientName = "",
        doctorName = "",
        pacientAge = 0,
        date = "",
        testLimits = TestLimits(
            reference = "Score < 19 => 5 vezes mais risco de quedas",
            cutNumber = 19,
            greatMsg = "Baixo Risco de Queda",
            mediumMsg = "Risco de Queda Iminente",
            highMsg = "Alto Risco de Queda"
        ),
        questions = listOf(
            Question(
                id = 1,
                text = "1. Equilíbrio sentado",
                alternatives = listOf(
                    AnswerOption("Escorrega", 0),
                    AnswerOption("Equilibrado", 1)
                )
            ),
            Question(
                id = 2,
                text = "2. Levantando",
                alternatives = listOf(
                    AnswerOption("Incapaz", 0),
                    AnswerOption("Usa os Braços", 1),
                    AnswerOption("Sem os Braços", 2)
                )
            ),
            Question(
                id = 3,
                text = "3. Tentativas de Levantar",
                alternatives = listOf(
                    AnswerOption("Incapaz", 0),
                    AnswerOption("Mais de uma vez", 1),
                    AnswerOption("Única tentativa", 2)
                )
            ),
            Question(
                id = 4,
                text = "4. Assim que levanta (3-5s)",
                alternatives = listOf(
                    AnswerOption("Desequilibrado", 0),
                    AnswerOption("Estável, mas usa suporte", 1),
                    AnswerOption("Estável sem suporte", 2)
                )
            ),
            Question(
                id = 5,
                text = "5. Equilíbrio em pé",
                alternatives = listOf(
                    AnswerOption("Desequilibrado", 0),
                    AnswerOption("Suporte ou base > 12cm", 1),
                    AnswerOption("Sem suporte e base estrela", 2)
                )
            ),
            Question(
                id = 6,
                text = "6. Teste dos 3 tempos",
                alternatives = listOf(
                    AnswerOption("Começa a cair", 0),
                    AnswerOption("Agarra ou balança os braços", 1),
                    AnswerOption("Equilibrado", 2)
                )
            ),
            Question(
                id = 7,
                text = "7. Olhos fechados",
                alternatives = listOf(
                    AnswerOption("Desequilibrado/Instável", 0),
                    AnswerOption("Equilibrado", 1)
                )
            ),
            Question(
                id = 8,
                text = "8. Girando 360 graus",
                alternatives = listOf(
                    AnswerOption("Passos descontinuos", 0),
                    AnswerOption("Passos continuos", 1),
                    AnswerOption("Instável", 0),
                    AnswerOption("Estável", 1)
                )
            ),
            Question(
                id = 9,
                text = "9. Sentando",
                alternatives = listOf(
                    AnswerOption("Inseguro (erra a distância, cai na cadeira)", 0),
                    AnswerOption("Usa os braços ou movimentação abrupta", 1),
                    AnswerOption("Seguro, movimentação suave", 2)
                )
            ),
            // Section 2: Marcha
            Question(
                id = 10,
                text = "10. Início da marcha",
                alternatives = listOf(
                    AnswerOption("Hesitação ou várias tentativas", 0),
                    AnswerOption("Sem hesitação", 1)
                )
            ),
            Question(
                id = 11,
                text = "11. Comprimento dos passos",
                alternatives = emptyList(),
                subQuestions = listOf(
                    Question(
                        id = 11,
                        text = "a) Pé esquerdo",
                        alternatives = listOf(
                            AnswerOption("Não ultrapassa o pé esquerdo", 0),
                            AnswerOption("Ultrapassa o pé esquerdo", 1),
                            AnswerOption("Não sai completamente do chão", 0),
                            AnswerOption("Sai completamente do chão", 1)
                        )
                    ),
                    Question(
                        id = 11,
                        text = "b) Pé direito",
                        alternatives = listOf(
                            AnswerOption("Não ultrapassa o pé direito", 0),
                            AnswerOption("Ultrapassa o pé direito", 1),
                            AnswerOption("Não sai completamente do chão", 0),
                            AnswerOption("Sai completamente do chão", 1)
                        )
                    )
                )
            ),
            Question(
                id = 13,
                text = "12. Simetria dos passos",
                alternatives = listOf(
                    AnswerOption("Passos diferentes", 0),
                    AnswerOption("Passos semelhantes", 1)
                )
            ),
            Question(
                id = 14,
                text = "13. Continuidade dos passos",
                alternatives = listOf(
                    AnswerOption("Paradas ou descontinuos", 0),
                    AnswerOption("Passos contínuos", 1)
                )
            ),
            Question(
                id = 15,
                text = "14. Direção",
                alternatives = listOf(
                    AnswerOption("Desvio nítido", 0),
                    AnswerOption("Desvio leve/moderado ou uso de apoio", 1),
                    AnswerOption("Linha reta sem apoio", 2)
                )
            ),
            Question(
                id = 16,
                text = "15. Tronco",
                alternatives = listOf(
                    AnswerOption("Balanço grave ou uso de apoio", 0),
                    AnswerOption("Flexão dos joelhos/dorso ou abertura dos braços", 1),
                    AnswerOption("Sem flexão/balanço, não usa apoio", 2)
                )
            ),
            Question(
                id = 17,
                text = "16. Distância dos tornozelos",
                alternatives = listOf(
                    AnswerOption("Tornozelos separados", 0),
                    AnswerOption("Tornozelos quase se tocam", 1),
                    AnswerOption("Outra opção", 2)
                )
            )
        ))
}