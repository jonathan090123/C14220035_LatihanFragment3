package uts.c14220035.c14220035_latihanfragment3

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlin.random.Random

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

        // Menyiapkan daftar tombol di grid
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
        // Ambil nilai batas awal dari SharedPreferences
        val sharedPreferences = activity?.getSharedPreferences("GamePreferences", Context.MODE_PRIVATE)
        val minRange = sharedPreferences?.getInt("minRange", 1) ?: 1

        // Buat daftar angka dari batas awal sampai 5, setiap angka muncul 2 kali
        val numbers = (minRange..5).flatMap { listOf(it, it) }.shuffled().toMutableList()

        // Acak susunan angka di tombol
        buttons.forEachIndexed { index, button ->
            button.text = numbers[index].toString()
            button.setOnClickListener { onNumberButtonClick(button) }
            button.isEnabled = true
            button.visibility = View.VISIBLE
        }

        // Reset variabel permainan
        lastSelectedValue = null
        lastButton = null
        matchedPairs = 0
    }

    private fun onNumberButtonClick(button: Button) {
        val selectedValue = button.text.toString()

        if (lastSelectedValue == null) {
            // Pilih angka pertama
            lastSelectedValue = selectedValue
            lastButton = button
            button.isEnabled = false
        } else {
            // Pilih angka kedua
            if (selectedValue == lastSelectedValue) {
                // Jika angka sama, skor +10
                score += 10
                matchedPairs++
                button.isEnabled = false
                lastButton?.isEnabled = false
            } else {
                // Jika angka beda, skor -5
                score -= 5
                lastButton?.isEnabled = true
            }

            // Perbarui skor di tampilan
            tvScoreValue.text = score.toString()
            lastSelectedValue = null
            lastButton = null
        }

        // Cek apakah permainan selesai (4 pasang sudah cocok)
        if (matchedPairs == 4) {
            navigateToResult()
        }
    }

    private fun navigateToResult() {
        // Navigasi ke fragment hasil dengan skor akhir
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
