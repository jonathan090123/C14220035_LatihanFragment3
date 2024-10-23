package uts.c14220035.c14220035_latihanfragment3

import  android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [fTebakAngka.newInstance] factory method to
 * create an instance of this fragment.
 */
class fTebakAngka : Fragment() {
    private var score = 50
    private var randomRange = 5  // Default angka random 1-5
    private lateinit var scoreTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_f_tebak_angka, container, false)

        scoreTextView = view.findViewById(R.id.scoreTextView)
        val btnStartGame = view.findViewById<Button>(R.id.btnStartGame)

        btnStartGame.setOnClickListener {
            startGame()
        }

        return view
    }

    private fun startGame() {
        // Mulai permainan tebak angka
        scoreTextView.text = "Skor: $score"
        // Logika permainan tebak angka akan di sini
        // Jika tebakan benar tambah skor, jika salah kurangi skor
    }
}