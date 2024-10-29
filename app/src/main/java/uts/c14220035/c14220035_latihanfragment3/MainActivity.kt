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
            resetGameAndReturnToGameFragment() // Reset game saat tombol Game ditekan
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

    // Fungsi untuk mereset game dan kembali ke GameFragment
    fun resetGameAndReturnToGameFragment() {
        finalScore = 0 // Reset skor
        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE) // Clear back stack
        loadFragment(GameFragment()) // Memuat ulang GameFragment
    }

    fun updateFinalScore(score: Int) {
        finalScore = score
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .addToBackStack(null)
            .commit()
    }
}
