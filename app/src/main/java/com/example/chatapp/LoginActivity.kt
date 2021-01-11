package com.example.chatapp

import android.os.Bundle
import com.example.chatapp.databinding.ActivityLoginBinding


class LoginActivity : BaseActivity(){

    private val binding : ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        init()
    }

    private fun init(){
        binding.tvLogin.setOnClickListener {

        }
    }


    private fun validate() : Boolean {
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
        }
        return true
    }
}