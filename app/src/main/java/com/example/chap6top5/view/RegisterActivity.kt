package com.example.chap6top5.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chap6top5.DataStoreLogin
import com.example.chap6top5.R
import com.example.chap6top5.databinding.ActivityRegisterBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {
    lateinit var binding : ActivityRegisterBinding
    lateinit var dataLogin : DataStoreLogin

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dataLogin = DataStoreLogin(this)

        binding.btnDaftar.setOnClickListener {
            var saveEmail = binding.registEmail.text.toString()
            var saveUser = binding.registUser.text.toString()
            var savePw = binding.inputRegistPw.text.toString()
            var getUpw = binding.inputRegistUpw.text.toString()

            GlobalScope.launch {
                dataLogin.saveData(saveUser,savePw,saveEmail)
                startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
            }
        }
    }
}