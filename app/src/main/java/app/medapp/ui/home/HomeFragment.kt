package app.medapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import app.medapp.R
import app.medapp.databinding.FragmentHomeBinding
import app.medapp.ui.QuizAdapter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    // List of test names in the order matching your TestsRepository:
    private val quizList = listOf(
        "Escala Tinete",
        "Escala de depressão geriátrica (GDS)",
        "Escala de Lawton",
        "Mini-exame do Estado Mental (MEEM)",
        "Teste do Sussurro",
        "Índice de KATZ – Atividades Básicas de Vida Diária"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = QuizAdapter(quizList) { quizName ->
            // Instead of hardcoding, use the index to derive test ID.
            val index = quizList.indexOf(quizName)
            val testId = index + 1  // assuming the list order matches test IDs
            val action = HomeFragmentDirections.actionHomeToGenericTestFragment(testId)
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
