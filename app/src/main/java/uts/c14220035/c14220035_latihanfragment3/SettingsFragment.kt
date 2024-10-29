package uts.c14220035.c14220035_latihanfragment3

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment

class SettingsFragment : Fragment() {

    private lateinit var etMinRange: EditText
    private lateinit var btnSave: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        etMinRange = view.findViewById(R.id.etMinRange)
        btnSave = view.findViewById(R.id.btnSave)

        btnSave.setOnClickListener {
            val minRangeText = etMinRange.text.toString()

            if (minRangeText.isNotEmpty() && minRangeText.toIntOrNull() != null) {
                val minRange = minRangeText.toInt()

                if (minRange > 0) {
                    saveMinRange(minRange)

                    Toast.makeText(context, "Batas awal berhasil disimpan!", Toast.LENGTH_SHORT).show()

                    loadFragment(GameFragment())
                } else {
                    Toast.makeText(context, "Masukkan angka lebih dari 0!", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(context, "Masukkan angka yang valid!", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

    private fun saveMinRange(minRange: Int) {
        val sharedPreferences = activity?.getSharedPreferences("game_prefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences?.edit()
        editor?.putInt("minRange", minRange)
        editor?.apply()
    }
    
    private fun loadFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .addToBackStack(null)
            .commit()
    }
}
