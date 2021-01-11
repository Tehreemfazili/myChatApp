package com.example.chatapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isEmpty
import com.example.chatapp.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private val binding : ActivityRegisterBinding by lazy {
        ActivityRegisterBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        init()
    }

    private fun init(){
        if (validate()){
        binding.imgRegister.setOnClickListener {

        }
        }
    }

    private fun validate() : Boolean{
        when{
          binding.etUsername.text.isNullOrEmpty()-> {
              binding.tilUsername.setErrorMessage(getString(R.string.empty_username_error))
              binding.etUsername.requestFocus()
              return false
          }
            binding.etPassword.text.isNullOrEmpty()-> {
                binding.tilPassword.setErrorMessage(getString(R.string.empty_password_error))
                binding.etPassword.requestFocus()
                return false
            }
            binding.etConfirmPassword.text.isNullOrEmpty()-> {
                binding.tilConfirmPassword.setErrorMessage(getString(R.string.empty_confirm_password_error))
                binding.etConfirmPassword.requestFocus()
                return false
            }
        }
      return true
    }
}