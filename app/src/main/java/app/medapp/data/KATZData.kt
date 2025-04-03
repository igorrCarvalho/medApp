package app.medapp.data

import app.medapp.data.models.AnswerOption
import app.medapp.data.models.Question
import app.medapp.data.models.Test
import app.medapp.data.models.TestLimits
import app.medapp.R
import app.medapp.data.models.ReferenceMapping

object KATZData {
    val KATZTest = Test(
        id = 2,
        testName = "Índice de KATZ – Atividades Básicas de Vida Diária",
        pacientName = "",
        doctorName = "",
        pacientAge = 0,
        date = "",
        testLimits = TestLimits(
            reference = listOf(
                "Independente: 0 pontos",
                "Dependente para UMA atividade: 1 ponto",
                "Dependente para DUAS atividades: 2 pontos",
                "Dependente para TRÊS atividades: 3 pontos",
                "Dependente para QUATRO atividades: 4 pontos",
                "Dependente para CINCO atividades: 5 pontos",
                "Dependente para SEIS atividades: 6 pontos",
            ),
            resultMappings = listOf(
                ReferenceMapping(
                    minScore = 0,
                    maxScore = 0,
                    message = "Independente"
                ),
                ReferenceMapping(
                    minScore = 1,
                    maxScore = 1,
                    message = "Dependente para UMA atividade"
                ),
                ReferenceMapping(
                    minScore = 2,
                    maxScore = 2,
                    message = "Dependente para DUAS atividades"
                ),
                ReferenceMapping(
                    minScore = 3,
                    maxScore = 3,
                    message = "Dependente para TRÊS atividades"
                ),
                ReferenceMapping(
                    minScore = 4,
                    maxScore = 4,
                    message = "Dependente para QUATRO atividades"
                ),
                ReferenceMapping(
                    minScore = 5,
                    maxScore = 5,
                    message = "Dependente para CINCO atividades"
                ),
                ReferenceMapping(
                    minScore = 6,
                    maxScore = 6,
                    message = "Dependente para SEIS atividades"
                ),
            )
        ),
        questions = listOf(
            Question(
                id = 1,
                text = "1. Tomar banho (leito, banheira ou chuveiro).",
                alternatives = listOf(
                    AnswerOption("Não recebe ajuda - (Independente)", 0),
                    AnswerOption("Recebe ajuda para lavar apenas uma parte do corpo (exemplo, as costas ou uma perna) - (Necessita de Assistência)", 0),
                    AnswerOption("Recebe ajuda para lavar mais de uma parte do corpo, ou não toma banho sozinho - (Dependente)", 1)
                )
            ),
            Question(
                id = 2,
                text = "2. Vestir-se (pega roupa, inclusive peças íntimas, nos armários e gavetas, e manuseia fecho, inclusive os de órteses e próteses, quando forem utilizadas)",
                alternatives = listOf(
                    AnswerOption("Pega as roupas e veste-se completamente, sem ajuda - (Independente)", 0),
                    AnswerOption("Pega as roupas e veste-se sem ajuda, exceto para amarrar os sapatos - (Necessita de Assistência)", 0),
                    AnswerOption("Recebe ajuda para pegar as roupas ou vestir-se, ou permanece parcial ou completamente sem roupa - (Dependente)", 1)
                )
            ),
            Question(
                id = 3,
                text = "3. Uso do vaso sanitário (Ida ao banheiro ou local equivalente para evacuar e urinar; higiene íntima e arrumação das roupas)",
                alternatives = listOf(
                    AnswerOption("Vai ao banheiro ou lugar equivalente, limpa-se a ajeita as roupas sem ajuda (pode ser objetos para apoio como bengala, andador ou cadeira de rodas e pode usar comadre ou urinol à noite, esvaziando-o de manhã) - (Independente)", 0),
                    AnswerOption("Recebe ajuda para ir ao banheiro ou local equivalente, para limpar-se ou para ajeitar as roupas após eliminações, ou para usar a comadre / urinol - (Necessita de Assistência)", 0),
                    AnswerOption("Não vai ao banheiro ou equivalente para eliminação fisiológicas - (Dependente)", 1)
                )
            ),
            Question(
                id = 4,
                text = "4. Transferências",
                alternatives = listOf(
                    AnswerOption("Deita-se e sai da cama, senta-se e levanta-se da cadeira sem ajuda ( pode estar usando objeto para apoio como bengala, andador - (Independente)", 0),
                    AnswerOption("Deita-se e sai da cama e/ou senta-se e levanta-se da cadeira com ajuda - (Necessita de Assistência)", 0),
                    AnswerOption("Não sai da cama - (Dependente)", 1)
                )
            ),
            Question(
                id = 5,
                text = "5. Continência",
                alternatives = listOf(
                    AnswerOption("Controla inteiramente a micção e a evacuação - (Independente)", 0),
                    AnswerOption("Tem “acidentes” ocasionais - (Necessita de Assistência)", 0),
                    AnswerOption("Necessita de ajuda para manter o controle da micção e evacuação; usa cateter ou é incontinente - (Dependente)", 1)
                )
            ),
            Question(
                id = 6,
                text = "6. Alimentação",
                alternatives = listOf(
                    AnswerOption("Alimenta-se sem ajuda - (Independente)", 0),
                    AnswerOption("Alimenta-se sozinho, mas recebe ajuda para cortar carne ou passar manteiga no pão - (Necessita de Assistência)", 0),
                    AnswerOption("Recebe ajuda para alimentar-se, ou é alimentado parcialmente ou completamente pelo uso de cateteres ou fluídos intra venoso - (Dependente)", 1)
                )
            )
        ))
}