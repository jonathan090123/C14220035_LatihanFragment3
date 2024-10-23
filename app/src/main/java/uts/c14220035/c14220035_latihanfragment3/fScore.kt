package uts.c14220035.c14220035_latihanfragment3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [fScore.newInstance] factory method to
 * create an instance of this fragment.
 */
class fScore : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var finalScore: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_f_score, container, false)
        val scoreTextView = view.findViewById<TextView>(R.id.finalScoreTextView)

        // Ambil skor akhir dan tampilkan
        scoreTextView.text = "Skor Akhir: $finalScore"
        return view
    }

    fun setFinalScore(score: Int) {
        finalScore = score
    }
}

