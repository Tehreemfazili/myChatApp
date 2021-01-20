package com.example.chatapp

import android.content.Intent
import android.os.Bundle
import com.example.chatapp.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth


class LoginActivity : BaseActivity() {

    private val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        init()
    }

    private fun init() {

        binding.tvLogin.setOnClickListener {
            if (validate()) {

                val email = binding.etEmail.text.toString()
                val password = binding.etPassword.text.toString()

                val auth = FirebaseAuth.getInstance()
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (!it.isSuccessful) {
                        return@addOnCompleteListener
                    }

                    startActivity(
                        Intent(
                            this,
                            MainActivity::class.java
                        ).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                            .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                    )

                }
            }
        }

        binding.tvCreateAccount.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }


    private fun validate(): Boolean {
        when {
            binding.etEmail.text.isNullOrEmpty() -> {
                binding.tilEmail.setErrorMessage(getString(R.string.empty_email_error))
                binding.etEmail.requestFocus()
                return false
            }
            binding.etPassword.text.isNullOrEmpty() -> {
                binding.tilPassword.setErrorMessage(getString(R.string.empty_password_error))
                binding.etPassword.requestFocus()
                return false
            }
        }
        return true
    }
}