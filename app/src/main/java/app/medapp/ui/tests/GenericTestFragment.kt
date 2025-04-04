package app.medapp.ui.generic

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import app.medapp.data.TestsRepository
import app.medapp.data.models.Test
import app.medapp.databinding.FragmentTinettiBinding
import app.medapp.ui.tinetti.TinettiAdapter
import app.medapp.utils.PdfGenerator
import app.medapp.ui.PdfPreviewActivity
import android.content.Intent
import app.medapp.data.models.TestLimits;
import app.medapp.data.models.ReferenceMapping;

class GenericTestFragment : Fragment() {

    private var _binding: FragmentTinettiBinding? = null
    private val binding get() = _binding!!

    // Map to store user answers (questionId -> selected value)
    private val answersMap = mutableMapOf<Int, Int>()

    // The Test object loaded dynamically from repository.
    private lateinit var currentTest: Test

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Retrieve testId from arguments (using Safe Args)
        val testId = arguments?.getInt("testId") ?: 1
        currentTest = TestsRepository.getTestById(testId)
    }

    fun getTestLimitsForEducation(educationAnswer: String): TestLimits {
        return when (educationAnswer) {
            "Analfabeto" -> TestLimits(
                reference = listOf(
                    "Normal: 19 - 30 pontos",
                    "Suspeita de perda cognitiva: 0 - 18 pontos"
                ),
                resultMappings = listOf(
                    ReferenceMapping(minScore = 19, maxScore = 30, message = "Normal"),
                    ReferenceMapping(minScore = 0, maxScore = 18, message = "Suspeita de perda cognitiva")
                )
            )
            "1 a 3 anos" -> TestLimits(
                reference = listOf(
                    "Normal: 23 - 30 pontos",
                    "Suspeita de perda cognitiva: 0 - 22 pontos"
                ),
                resultMappings = listOf(
                    ReferenceMapping(minScore = 23, maxScore = 30, message = "Normal"),
                    ReferenceMapping(minScore = 0, maxScore = 22, message = "Suspeita de perda cognitiva")
                )
            )
            "4 a 7 anos" -> TestLimits(
                reference = listOf(
                    "Normal: 24 - 30 pontos",
                    "Suspeita de perda cognitiva: 0 - 23 pontos"
                ),
                resultMappings = listOf(
                    ReferenceMapping(minScore = 24, maxScore = 30, message = "Normal"),
                    ReferenceMapping(minScore = 0, maxScore = 23, message = "Suspeita de perda cognitiva")
                )
            )
            "Acima de 7 anos" -> TestLimits(
                reference = listOf(
                    "Normal: 28 - 30 pontos",
                    "Suspeita de perda cognitiva: 0 - 27 pontos"
                ),
                resultMappings = listOf(
                    ReferenceMapping(minScore = 28, maxScore = 30, message = "Normal"),
                    ReferenceMapping(minScore = 0, maxScore = 27, message = "Suspeita de perda cognitiva")
                )
            )
            else -> {
                // Fallback limits or throw an exception if unexpected
                TestLimits(
                    reference = listOf("Referência não definida"),
                    resultMappings = emptyList()
                )
            }
        }
    }


    override fun onCreateView(
        inflater: android.view.LayoutInflater, container: android.view.ViewGroup?,
        savedInstanceState: Bundle?
    ): android.view.View {
        _binding = app.medapp.databinding.FragmentTinettiBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun updateSubmitButtonState() {
        binding.buttonSubmit.isEnabled = allQuestionsAnswered(currentTest)
    }

    private fun allQuestionsAnswered(test: Test): Boolean {
        test.questions.forEach { question ->
            if (!question.subQuestions.isNullOrEmpty()) {
                // For composite questions, check each subquestion
                question.subQuestions.forEach { subQuestion ->
                    if (answersMap[subQuestion.id] == null) return false
                }
            } else {
                // For normal questions, check if an answer exists
                if (answersMap[question.id] == null) return false
            }
        }
        return true
    }

    override fun onViewCreated(view: android.view.View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateSubmitButtonState()
        binding.testTitle.text = currentTest.testName

        // Capture user input for basic data.
        binding.editPacientName.doOnTextChanged { text, _, _, _ ->
            currentTest = currentTest.copy(pacientName = text.toString())
        }
        binding.editDoctorName.doOnTextChanged { text, _, _, _ ->
            currentTest = currentTest.copy(doctorName = text.toString())
        }
        binding.editPacientAge.doOnTextChanged { text, _, _, _ ->
            val age = text.toString().toIntOrNull() ?: 0
            currentTest = currentTest.copy(pacientAge = age)
        }
        binding.editDate.addTextChangedListener(object : TextWatcher {
            private var isEditing = false
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { }
            override fun afterTextChanged(s: Editable?) {
                if (isEditing) return
                isEditing = true
                var clean = s.toString().replace("[^\\d]".toRegex(), "")
                if (clean.length > 8) clean = clean.substring(0, 8)
                val formatted = when {
                    clean.length >= 5 -> clean.substring(0, 2) + "/" + clean.substring(2, 4) + "/" + clean.substring(4)
                    clean.length >= 3 -> clean.substring(0, 2) + "/" + clean.substring(2)
                    else -> clean
                }
                binding.editDate.setText(formatted)
                binding.editDate.setSelection(formatted.length)
                currentTest = currentTest.copy(date = formatted)
                isEditing = false
            }
        })

        // Setup RecyclerView with questions from the Test object.
        val adapter = TinettiAdapter(currentTest.questions) { questionId, selectedOption ->
            if (selectedOption != null) {
                answersMap[questionId] = selectedOption.value
            } else {
                answersMap.remove(questionId)
            }
            updateSubmitButtonState()  // Update button state based on current answers
        }
        binding.recyclerViewTinetti.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewTinetti.adapter = adapter

        // Submit button: calculate total score and generate PDF.
        binding.buttonSubmit.setOnClickListener {
            if (!allQuestionsAnswered(currentTest)) {
                Toast.makeText(requireContext(), "Por favor, responda todas as perguntas antes de enviar.", Toast.LENGTH_SHORT).show()
            } else {
                // For MEEM test (testId == 4), update testLimits based on the education answer.
                if (currentTest.id == 4) {
                    // Find the education question (assumed id == 0)
                    val educationQuestion = currentTest.questions.find { it.id == 0 }
                    // Determine which alternative is selected
                    val selectedEducation = educationQuestion?.alternatives?.find { it.isSelected }?.text
                    if (selectedEducation != null) {
                        // Update currentTest's testLimits using the helper function.
                        currentTest = currentTest.copy(
                            testLimits = getTestLimitsForEducation(selectedEducation)
                        )
                    }
                }
                val totalScore = answersMap.values.sum()
                val pdfFilePath = PdfGenerator.generatePdf(requireContext(), currentTest, answersMap, totalScore)
                if (pdfFilePath != null) {
                    // Compute the final result message based on totalScore
                    val finalMsg = currentTest.testLimits.resultMappings.find { mapping ->
                        totalScore in mapping.minScore..mapping.maxScore
                    }?.message ?: "Sem mensagem definida"

                    val intent = Intent(requireContext(), PdfPreviewActivity::class.java).apply {
                        putExtra("pdfFilePath", pdfFilePath)
                        if (currentTest.id != 6) {
                            putExtra("totalScore", totalScore)
                        }
                        putExtra("finalMsg", finalMsg)
                    }
                    startActivity(intent)
                } else {
                    Toast.makeText(requireContext(), "Erro ao gerar PDF", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
