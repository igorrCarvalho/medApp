package app.medapp.ui.tinetti

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import app.medapp.R
import app.medapp.data.AnswerOption
import app.medapp.data.Question
import app.medapp.databinding.ItemQuestionBinding
import com.google.android.material.checkbox.MaterialCheckBox

class TinettiAdapter(
    private val questions: List<Question>,
    private val onOptionSelected: (questionId: Int, selectedOption: AnswerOption) -> Unit
) : RecyclerView.Adapter<TinettiAdapter.TinettiViewHolder>() {

    inner class TinettiViewHolder(val binding: ItemQuestionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(question: Question) {
            // Set question text color
            binding.questionText.text = question.text
            binding.questionText.setTextColor(
                ContextCompat.getColor(binding.root.context, R.color.foreground)
            )

            // Clear any old views in the container
            binding.alternativesContainer.removeAllViews()

            // Check if question has subQuestions
            if (!question.subQuestions.isNullOrEmpty()) {
                // For each sub-question
                question.subQuestions.forEach { subQ ->
                    // Create a label for the sub-question
                    val subQuestionLabel = TextView(binding.root.context).apply {
                        text = subQ.text
                        textSize = 14f
                        setPadding(0, 8, 0, 4)
                        // Sub-question label color
                        setTextColor(ContextCompat.getColor(context, R.color.foreground))
                    }
                    binding.alternativesContainer.addView(subQuestionLabel)

                    // For each alternative in the sub-question
                    subQ.alternatives.forEach { alt ->
                        val materialCheckBox = MaterialCheckBox(binding.root.context).apply {
                            text = alt.text
                            isChecked = alt.isSelected

                            // Set the label text color
                            setTextColor(
                                ContextCompat.getColor(context, R.color.muted_foreground)
                            )
                            // Use a color state list for the checkbox check mark
                            buttonTintList = ContextCompat.getColorStateList(context, R.color.checkbox_tint_selector)

                            setOnCheckedChangeListener { _, isChecked ->
                                if (isChecked) {
                                    // Only one selected per sub-question
                                    subQ.alternatives.forEach { it.isSelected = false }
                                    alt.isSelected = true
                                    notifyItemChanged(adapterPosition)
                                    onOptionSelected(subQ.id, alt)
                                } else {
                                    alt.isSelected = false
                                }
                            }
                        }
                        binding.alternativesContainer.addView(materialCheckBox)
                    }
                }
            } else {
                // Normal question (no sub-questions)
                question.alternatives.forEach { option ->
                    val materialCheckBox = MaterialCheckBox(binding.root.context).apply {
                        text = option.text
                        isChecked = option.isSelected

                        // Set the label text color
                        setTextColor(
                            ContextCompat.getColor(context, R.color.muted_foreground)
                        )
                        // Use a color state list for the checkbox check mark
                        buttonTintList = ContextCompat.getColorStateList(context, R.color.checkbox_tint_selector)

                        setOnCheckedChangeListener { _, isChecked ->
                            if (isChecked) {
                                question.alternatives.forEach { it.isSelected = false }
                                option.isSelected = true
                                notifyItemChanged(adapterPosition)
                                onOptionSelected(question.id, option)
                            } else {
                                option.isSelected = false
                            }
                        }
                    }
                    binding.alternativesContainer.addView(materialCheckBox)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TinettiViewHolder {
        val binding = ItemQuestionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TinettiViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TinettiViewHolder, position: Int) {
        holder.bind(questions[position])
    }

    override fun getItemCount() = questions.size
}
