package app.medapp.data

import app.medapp.data.models.AnswerOption
import app.medapp.data.models.Question
import app.medapp.data.models.Test
import app.medapp.data.models.TestLimits
import app.medapp.R

object MEEMData {
    val MEEMTest = Test(
        id = 4,
        testName = "Mini-Exame do Estado Mental (MEEM)",
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
                text = "1. Qual é o ano, estação, dia da semana, dia do mês e mês.",
                alternatives = listOf(
                    AnswerOption("Paciente não acertou nenhuma", 0),
                    AnswerOption("Paciente acertou uma", 1),
                    AnswerOption("Paciente acertou duas", 2),
                    AnswerOption("Paciente acertou três", 3),
                    AnswerOption("Paciente acertou quatro", 4),
                    AnswerOption("Paciente acertou cinco", 5),
                )
            ),
            Question(
                id = 2,
                text = "2. Onde estamos? País, Estado, Cidade, Rua ou Local, nº ou Andar.",
                alternatives = listOf(
                    AnswerOption("Paciente não acertou nenhuma", 0),
                    AnswerOption("Paciente acertou uma", 1),
                    AnswerOption("Paciente acertou duas", 2),
                    AnswerOption("Paciente acertou três", 3),
                    AnswerOption("Paciente acertou quatro", 4),
                    AnswerOption("Paciente acertou cinco", 5),
                )
            ),
            Question(
                id = 3,
                text = "3. Diga ao paciente três palavras. Peça-lhe para prestar atenção, pois terá que repetir mais tarde. Pergunte pelas três palavras após dizê-las. Repita até cinco vezes e conte quantas palavras ele acertou",
                alternatives = listOf(
                    AnswerOption("Paciente não acertou nenhuma", 0),
                    AnswerOption("Paciente acertou uma", 1),
                    AnswerOption("Paciente acertou duas", 2),
                    AnswerOption("Paciente acertou três", 3),
                )
            ),
            Question(
                id = 4,
                text = "4. Peça ao paciente para subtrair 100-7, sucessivamente, 5 vezes ((100 - 7) -> (93 - 7) -> (86 - 7) -> (79 - 7) -> (72 - 7) -> 65)",
                alternatives = listOf(
                    AnswerOption("Paciente não acertou nenhuma", 0),
                    AnswerOption("Paciente acertou uma", 1),
                    AnswerOption("Paciente acertou duas", 2),
                    AnswerOption("Paciente acertou três", 3),
                    AnswerOption("Paciente acertou quatro", 4),
                    AnswerOption("Paciente acertou cinco", 5),
                )
            ),
            Question(
                id = 5,
                text = "5. Perguntar pelas 3 palavras da questão 3 novamente",
                alternatives = listOf(
                    AnswerOption("Paciente não acertou nenhuma", 0),
                    AnswerOption("Paciente acertou uma", 1),
                    AnswerOption("Paciente acertou duas", 2),
                    AnswerOption("Paciente acertou três", 3),
                )
            ),
            Question(
                id = 6,
                text = "6. Identificar lápis e relógio de pulso (sem estar no pulso).",
                alternatives = listOf(
                    AnswerOption("Paciente não acertou nenhuma", 0),
                    AnswerOption("Paciente acertou uma", 1),
                    AnswerOption("Paciente acertou duas", 2)
                )
            ),
            Question(
                id = 7,
                text = "7. Repetir: 'Nem aqui, nem ali, nem lá'.",
                alternatives = listOf(
                    AnswerOption("Conseguiu", 1),
                    AnswerOption("Não Conseguiu", 0)
                )
            ),
            Question(
                id = 9,
                text = "8. Seguir o comando de três estágios: “Pegue o papel com a mão direita, dobre ao meio e ponha no chão”. (Falar essa frase de forma inteira e apenas uma vez).",
                alternatives = listOf(
                    AnswerOption("Não conseguiu nenhum", 0),
                    AnswerOption("Conseguiu um", 1),
                    AnswerOption("Conseguiu dois", 2),
                    AnswerOption("Conseguiu três", 3)
                )
            ),
            // Section 2: Marcha
            Question(
                id = 10,
                text = "9. Ler (“só com os olhos”) e executar: FECHE OS OLHOS",
                alternatives = listOf(
                    AnswerOption("Não conseguiu", 0),
                    AnswerOption("Conseguiu", 1)
                )
            ),
            Question(
                id = 11,
                text = "10. Escrever uma frase (um pensamento, ideia completa)",
                alternatives = listOf(
                    AnswerOption("Não Conseguiu", 0),
                    AnswerOption("Conseguiu", 1)
                )
            ),
            Question(
                id = 12,
                text = "11. Copiar o desenho:",
                imageResId = R.drawable.image_to_copy,
                alternatives = listOf(
                    AnswerOption("Conseguiu", 1),
                    AnswerOption("Não conseguiu", 0)
                )
            )
        ))
}