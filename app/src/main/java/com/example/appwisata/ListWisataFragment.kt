package com.example.appwisata

import android.content.Intent
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
    val KEYUSER = "idUser"
    val KEYEMAIL = "email"
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
        idUser = activity?.getIntent()?.getStringExtra(SignInGoogleActivity.KEYIDUSER)
        email = activity?.getIntent()?.getStringExtra(SignInGoogleActivity.KEYEMAIL)

        editor.putString(KEYUSER,idUser)
        editor.putString(KEYEMAIL,email)
        editor.apply();

        binding.fab.setOnClickListener {
            startActivity(Intent(context,FormActivity::class.java))
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}