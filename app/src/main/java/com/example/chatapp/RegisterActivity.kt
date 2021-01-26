package com.example.chatapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.chatapp.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class RegisterActivity : AppCompatActivity() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val binding: ActivityRegisterBinding by lazy {
        ActivityRegisterBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        init()
    }

    private fun init() {

            binding.imgRegister.setOnClickListener {
                if (validate()) {
                register()
            }
        }
    }

    private fun register() {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        val username = binding.etUsername.text.toString()
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { it ->
            if (!it.isSuccessful) {
                return@addOnCompleteListener
            }
            val fireBaseUser = auth.currentUser ?: return@addOnCompleteListener
            val userId = fireBaseUser.uid

            val reference = FirebaseDatabase.getInstance().getReference("userId").child(userId)

            val users: HashMap<String, String> = HashMap()
            users["userId"] = userId // works as users.put("userId", userId)
            users["username"] = username
            users["imageUrl"] = "default"

            reference.setValue(users).addOnCompleteListener {
                if (!it.isSuccessful) {
                    return@addOnCompleteListener
                }

                startActivity(
                    Intent(
                        this,
                        LoginActivity::class.java
                    ).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                )
            }
        }
    }

    private fun validate(): Boolean {
        when {
            binding.etUsername.text.isNullOrEmpty() -> {
                binding.tilUsername.setErrorMessage(getString(R.string.empty_username_error))
                binding.etUsername.requestFocus()
                return false
            }
            binding.etPassword.text.isNullOrEmpty() -> {
                binding.tilPassword.setErrorMessage(getString(R.string.empty_password_error))
                binding.etPassword.requestFocus()
                return false
            }
            binding.etPassword.length() < 8 -> {
                binding.tilPassword.setErrorMessage(getString(R.string.password_length_error))
                binding.etPassword.requestFocus()
                return false
            }
            binding.etConfirmPassword.text.isNullOrEmpty() -> {
                binding.tilConfirmPassword.setErrorMessage(getString(R.string.empty_confirm_password_error))
                binding.etConfirmPassword.requestFocus()
                return false
            }
            binding.etPassword.text.toString() != binding.etConfirmPassword.text.toString() -> {
                binding.tilConfirmPassword.setErrorMessage(getString(R.string.password_doesnt_match_error))
                binding.etConfirmPassword.requestFocus()
                return false
            }
        }
        return true
    }
}