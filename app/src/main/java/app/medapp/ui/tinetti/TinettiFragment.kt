package app.medapp.ui.tinetti

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import app.medapp.data.TinettiData
import app.medapp.databinding.FragmentTinettiBinding
import app.medapp.utils.PdfGenerator

class TinettiFragment : Fragment() {

    private var _binding: FragmentTinettiBinding? = null
    private val binding get() = _binding!!

    private val answersMap = mutableMapOf<Int, Int>()

    private var tinettiTest = TinettiData.tinettiTest

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTinettiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.editPacientName.doOnTextChanged { text, _, _, _ ->
            tinettiTest = tinettiTest.copy(pacientName = text.toString())
        }
        binding.editDoctorName.doOnTextChanged { text, _, _, _ ->
            tinettiTest = tinettiTest.copy(doctorName = text.toString())
        }
        binding.editPacientAge.doOnTextChanged { text, _, _, _ ->
            val age = text.toString().toIntOrNull() ?: 0
            tinettiTest = tinettiTest.copy(pacientAge = age)
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
                tinettiTest = tinettiTest.copy(date = formatted)
                isEditing = false
            }
        })

        val questions = tinettiTest.questions

        val adapter = TinettiAdapter(questions) { questionId, selectedOption ->
            answersMap[questionId] = selectedOption.value
        }
        binding.recyclerViewTinetti.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewTinetti.adapter = adapter

        binding.buttonSubmit.setOnClickListener {
            val totalScore = answersMap.values.sum()
            PdfGenerator.generatePdf(requireContext(), tinettiTest, answersMap, totalScore)
            Toast.makeText(requireContext(), "PDF generated", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
