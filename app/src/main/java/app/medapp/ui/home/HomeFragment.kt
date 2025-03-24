package app.medapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import app.medapp.databinding.FragmentHomeBinding
import app.medapp.ui.home.HomeFragmentDirections
import app.medapp.ui.QuizAdapter
import app.medapp.R

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val quizList = listOf(
        "Escala GDS 15",
        "Escala de Katz",
        "Escala de Lawton",
        "MEEM",
        "Teste MoCA",
        "Escala ASHA FACS",
        "Escala Tinete"
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
            if (quizName == "Escala Tinete") {
                findNavController().navigate(R.id.tinettiFragment)
            } else {
                val action = HomeFragmentDirections.actionHomeToQuiz(quizName)
                findNavController().navigate(action)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}