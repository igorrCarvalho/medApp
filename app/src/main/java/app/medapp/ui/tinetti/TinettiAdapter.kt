package app.medapp.ui.tinetti

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import app.medapp.R
import app.medapp.data.models.AnswerOption
import app.medapp.data.models.Question
import app.medapp.databinding.ItemQuestionBinding
import com.google.android.material.checkbox.MaterialCheckBox
import android.view.View

class TinettiAdapter(
    private val questions: List<Question>,
    private val onOptionSelected: (questionId: Int, selectedOption: AnswerOption) -> Unit
) : RecyclerView.Adapter<TinettiAdapter.TinettiViewHolder>() {

    inner class TinettiViewHolder(val binding: ItemQuestionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(question: Question) {
            // Set the question text.
            binding.questionText.text = question.text
            binding.questionText.setTextColor(
                ContextCompat.getColor(binding.root.context, R.color.foreground)
            )

            // Check if there's an image resource for this question.
            if (question.imageResId != null) {
                binding.questionImage.visibility = View.VISIBLE
                binding.questionImage.setImageResource(question.imageResId)
            } else {
                binding.questionImage.visibility = View.GONE
            }

            // Clear the alternatives container.
            binding.alternativesContainer.removeAllViews()

            // Then add answer options (handling composite questions as before).
            if (!question.subQuestions.isNullOrEmpty()) {
                question.subQuestions.forEach { subQ ->
                    val subQuestionLabel = TextView(binding.root.context).apply {
                        text = subQ.text
                        textSize = 14f
                        setPadding(0, 8, 0, 4)
                        setTextColor(ContextCompat.getColor(context, R.color.foreground))
                    }
                    binding.alternativesContainer.addView(subQuestionLabel)

                    subQ.alternatives.forEach { alt ->
                        val materialCheckBox = MaterialCheckBox(binding.root.context).apply {
                            text = alt.text
                            isChecked = alt.isSelected
                            setTextColor(
                                ContextCompat.getColor(context, R.color.muted_foreground)
                            )
                            buttonTintList = ContextCompat.getColorStateList(context, R.color.checkbox_tint_selector)
                            setOnCheckedChangeListener { _, isChecked ->
                                if (isChecked) {
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
                question.alternatives.forEach { option ->
                    val materialCheckBox = MaterialCheckBox(binding.root.context).apply {
                        text = option.text
                        isChecked = option.isSelected
                        setTextColor(
                            ContextCompat.getColor(context, R.color.muted_foreground)
                        )
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
