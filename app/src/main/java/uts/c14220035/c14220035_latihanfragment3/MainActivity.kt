package uts.c14220035.c14220035_latihanfragment3

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class MainActivity : AppCompatActivity() {

    private var finalScore: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnGame = findViewById<Button>(R.id.btnGame)
        val btnScore = findViewById<Button>(R.id.btnScore)
        val btnSettings = findViewById<Button>(R.id.btnSettings)

        btnGame.setOnClickListener {
            resetGameAndReturnToGameFragment() // Atur ulang permainan setiap kali tombol Game ditekan
        }

        btnSettings.setOnClickListener {
            loadFragment(SettingsFragment())
        }

        btnScore.setOnClickListener {
            val scoreFragment = ScoreFragment()
            val bundle = Bundle()
            bundle.putInt("finalScore", finalScore)
            scoreFragment.arguments = bundle
            loadFragment(scoreFragment)
        }
    }

    fun resetGameAndReturnToGameFragment() {
        // Reset skor akhir dan memuat ulang GameFragment
        finalScore = 0
        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE) // Kosongkan back stack
        loadFragment(GameFragment()) // Mulai ulang GameFragment
    }

    fun updateFinalScore(score: Int) {
        // Memperbarui skor akhir setelah permainan selesai
        finalScore = score
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .addToBackStack(null)
            .commit()
    }
}
