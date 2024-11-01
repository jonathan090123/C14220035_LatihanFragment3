package uts.c14220035.c14220035_latihanfragment3

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlinx.coroutines.*

class GameFragment : Fragment() {

    private lateinit var tvScoreValue: TextView
    private lateinit var btnGiveUp: Button
    private lateinit var buttons: List<Button>
    private var score = 50
    private var lastSelectedValue: String? = null
    private var lastButton: Button? = null
    private var matchedPairs = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_game, container, false)

        tvScoreValue = view.findViewById(R.id.tvScoreValue)
        tvScoreValue.text = score.toString()

        btnGiveUp = view.findViewById(R.id.btnGiveUp)
        btnGiveUp.setOnClickListener {
            navigateToResult()
        }

        buttons = listOf(
            view.findViewById(R.id.btn1),
            view.findViewById(R.id.btn2),
            view.findViewById(R.id.btn3),
            view.findViewById(R.id.btn4),
            view.findViewById(R.id.btn5),
            view.findViewById(R.id.btn6),
            view.findViewById(R.id.btn7),
            view.findViewById(R.id.btn8),
            view.findViewById(R.id.btn9)
        )

        initializeGame()
        return view
    }

    private fun initializeGame() {
        val numbers = (1..5).flatMap { listOf(it, it) }.shuffled().toMutableList()

        buttons.forEachIndexed { index, button ->
            button.tag = numbers[index].toString()
            button.text = ""
            button.setOnClickListener { onNumberButtonClick(button) }
            button.isEnabled = true
            button.visibility = View.VISIBLE
        }

        lastSelectedValue = null
        lastButton = null
        matchedPairs = 0
        score = 50
        tvScoreValue.text = score.toString()
    }

    private fun onNumberButtonClick(button: Button) {
        val selectedValue = button.tag.toString()

        button.text = selectedValue

        if (lastButton == button) {
            return
        }

        if (lastSelectedValue == null) {
            // Pilih angka pertama
            lastSelectedValue = selectedValue
            lastButton = button
            button.isEnabled = false
        } else {
            if (selectedValue == lastSelectedValue) {
                score += 10
                matchedPairs++
                button.isEnabled = false
                lastButton?.isEnabled = false
            } else {
                score -= 5
                lastButton?.isEnabled = true
                hideButtonTextDelayed(button)
                hideButtonTextDelayed(lastButton)
            }

            tvScoreValue.text = score.toString()

            lastSelectedValue = null
            lastButton = null
        }

        if (matchedPairs == 4) {
            navigateToResult()
        }
    }

    private fun hideButtonTextDelayed(button: Button?) {
        CoroutineScope(Dispatchers.Main).launch {
            delay(500)
            button?.text = ""
        }
    }

    private fun navigateToResult() {
        (activity as MainActivity).updateFinalScore(score)
        val resultFragment = ScoreFragment()
        val bundle = Bundle()
        bundle.putInt("finalScore", score)
        resultFragment.arguments = bundle

        parentFragmentManager.beginTransaction()
            .replace(R.id.frameContainer, resultFragment)
            .addToBackStack(null)
            .commit()
    }
}
