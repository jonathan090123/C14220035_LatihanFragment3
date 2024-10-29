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
        // Inflate layout untuk SettingsFragment
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        etMinRange = view.findViewById(R.id.etMinRange)
        btnSave = view.findViewById(R.id.btnSave)

        // Set OnClickListener untuk tombol Save
        btnSave.setOnClickListener {
            val minRangeText = etMinRange.text.toString()  // Ambil input dari EditText

            // Validasi input apakah kosong atau tidak valid
            if (minRangeText.isNotEmpty() && minRangeText.toIntOrNull() != null) {
                val minRange = minRangeText.toInt()

                // Validasi apakah minRange lebih dari 0, agar tidak crash saat digunakan
                if (minRange > 0) {
                    // Simpan batas awal (minRange) ke SharedPreferences
                    saveMinRange(minRange)

                    // Berikan feedback bahwa data tersimpan
                    Toast.makeText(context, "Batas awal berhasil disimpan!", Toast.LENGTH_SHORT).show()

                    // Navigasi ke GameFragment
                    loadFragment(GameFragment())
                } else {
                    // Tampilkan pesan error jika angka di bawah 1
                    Toast.makeText(context, "Masukkan angka lebih dari 0!", Toast.LENGTH_SHORT).show()
                }
            } else {
                // Tampilkan pesan error jika input tidak valid
                Toast.makeText(context, "Masukkan angka yang valid!", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

    // Fungsi untuk menyimpan minRange menggunakan SharedPreferences
    private fun saveMinRange(minRange: Int) {
        val sharedPreferences = activity?.getSharedPreferences("game_prefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences?.edit()
        editor?.putInt("minRange", minRange)
        editor?.apply() // Simpan perubahan
    }

    // Fungsi untuk navigasi ke GameFragment
    private fun loadFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .addToBackStack(null)
            .commit()
    }
}
