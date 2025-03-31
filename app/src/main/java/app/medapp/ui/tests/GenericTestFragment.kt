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

    override fun onCreateView(
        inflater: android.view.LayoutInflater, container: android.view.ViewGroup?,
        savedInstanceState: Bundle?
    ): android.view.View {
        _binding = app.medapp.databinding.FragmentTinettiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: android.view.View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
            answersMap[questionId] = selectedOption.value
        }
        binding.recyclerViewTinetti.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewTinetti.adapter = adapter

        // Submit button: calculate total score and generate PDF.
        binding.buttonSubmit.setOnClickListener {
            val totalScore = answersMap.values.sum()
            PdfGenerator.generatePdf(requireContext(), currentTest, answersMap, totalScore)
            Toast.makeText(requireContext(), "PDF generated", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
