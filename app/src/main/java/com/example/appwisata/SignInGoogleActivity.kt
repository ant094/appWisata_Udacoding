package com.example.appwisata

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appwisata.databinding.ActivitySignInGoogleBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignInGoogleActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInGoogleBinding
    private lateinit var auth: FirebaseAuth
    lateinit var googleSignInClient: GoogleSignInClient

    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor

    val KEYEMAIL = "email"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInGoogleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configure Google Sign In

        auth = Firebase.auth

        // Session
        sharedPreferences = getSharedPreferences("Settings", 0)!!
        editor = sharedPreferences.edit()

        actionCheckSession()

        binding.btnSignInGoogle.setOnClickListener {
            signIn()
        }
    }
    // Config Google
    private fun signIn() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("603926176444-pg1riir743jmbonqvfp244edd777k3kr.apps.googleusercontent.com")
                .requestEmail()
                .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso);

        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == 1) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                firebaseAuthWithGoogle(account.idToken!!)
                account.email
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                // ...
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        val user = auth.currentUser

                        val intent = Intent(this, MainActivity::class.java)
                        intent.putExtra("idUser", sharedPreferences.getString("idUser",user?.uid))
                        intent.putExtra("email", sharedPreferences.getString("email",user?.email))
                        startActivity(intent)
                    } else {
                        // If sign in fails, display a message to the user.
                        // ...
                    }

                    // ...
                }
    }

    private fun actionCheckSession() {
        if (sharedPreferences.contains(KEYEMAIL)) {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("email", sharedPreferences.getString("email",""))
            intent.putExtra("idUser", sharedPreferences.getString("idUser",""))
            startActivity(intent)
            }
        }


}