package com.example.appwisata

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.appwisata.databinding.FragmentListWisataBinding


class ListWisataFragment : Fragment() {
    private var _binding: FragmentListWisataBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor : SharedPreferences.Editor
    val KEYEMAIL = "email"
    val KEYUSER = "idUser"
    var email : String? = null
    var idUser : String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListWisataBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPreferences = context?.getSharedPreferences("Settings", 0)!!
        editor = sharedPreferences.edit()
        email = activity?.getIntent()?.getStringExtra("email")
        idUser = activity?.getIntent()?.getStringExtra("idUser")

        editor.putString(KEYEMAIL,email)
        editor.putString(KEYUSER,idUser)
        editor.apply();

        Toast.makeText(requireContext(), sharedPreferences.getString(KEYEMAIL,""), Toast.LENGTH_SHORT).show()

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}