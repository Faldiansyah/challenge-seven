package com.example.chap6top5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chap6top5.databinding.ActivityMainBinding
import com.example.chap6top5.view.MakeupAdapter
import com.example.chap6top5.viewmodel.ViewModelMakeup
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var mGoogleSignInClient: GoogleSignInClient

    private val auth by lazy {
        FirebaseAuth.getInstance()
    }
    lateinit var binding : ActivityMainBinding
    lateinit var viewModel : ViewModelMakeup
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        mGoogleSignInClient= GoogleSignIn.getClient(this,gso)

        getDataMakeup()
        binding.btn1.setOnClickListener {
//            throw RuntimeException("Test Crash")
            mGoogleSignInClient.signOut().addOnCompleteListener {
                val intent= Intent(this, LoginActivity::class.java)
                Toast.makeText(this,"Logging Out", Toast.LENGTH_SHORT).show()
                startActivity(intent)
                finish()
            }
        }

    }
    fun getDataMakeup(){
        viewModel = ViewModelProvider(this).get(ViewModelMakeup::class.java)
        viewModel.getliveDataMakeup().observe(this,{
            if (it != null){
                binding.rvMakeup.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
                binding.rvMakeup.adapter = MakeupAdapter(it)
                val adapter = MakeupAdapter(it)
                adapter.notifyDataSetChanged()
            }
        })
        viewModel.callApiMakeup()
    }
}