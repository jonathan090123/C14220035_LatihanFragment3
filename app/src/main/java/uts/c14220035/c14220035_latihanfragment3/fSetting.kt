package uts.c14220035.c14220035_latihanfragment3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [fSetting.newInstance] factory method to
 * create an instance of this fragment.
 */
class fSetting : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var inputRange: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_f_setting, container, false)

        inputRange = view.findViewById(R.id.editTextRange)
        val btnSaveSettings = view.findViewById<Button>(R.id.btnSaveSettings)

        btnSaveSettings.setOnClickListener {
            val range = inputRange.text.toString().toIntOrNull()
            if (range != null && range > 0) {
                // Simpan range ke Fragment 1 (GameFragment)
                // Harus menerapkan mekanisme penyimpanan data seperti ViewModel atau SharedPreferences
            }
        }
        return view
    }
}

