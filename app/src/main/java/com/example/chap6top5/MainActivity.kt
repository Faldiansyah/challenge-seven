package com.example.chap6top5

import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chap6top5.databinding.ActivityMainBinding
import com.example.chap6top5.view.*
import com.example.chap6top5.viewmodel.ViewModelDataMhs
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    lateinit var dataLogin : DataStoreLogin
    lateinit var viewModel : ViewModelDataMhs

    lateinit var mGoogleSignInClient: GoogleSignInClient
    val Req_Code:Int=123
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        FirebaseApp.initializeApp(this)
//        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//            .requestIdToken(getString(R.string.default_web_client_id))
//            .requestEmail()
//            .build()
//        mGoogleSignInClient= GoogleSignIn.getClient(this,gso)
//        firebaseAuth= FirebaseAuth.getInstance()
//
//
//        binding.btnlogoutgoogle.setOnClickListener {
//            mGoogleSignInClient.signOut().addOnCompleteListener {
//                val intent= Intent(this, LoginActivity::class.java)
//                Toast.makeText(this,"Logging Out", Toast.LENGTH_SHORT).show()
//                startActivity(intent)
//                finish()
//            }
//        }

        dataLogin = DataStoreLogin(this)
        dataLogin.userName.asLiveData().observe(this,{
            binding.txtUsername.text = "Hello, "+ it.toString()
        })

        binding.btnProfile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }
        binding.btnAdd.setOnClickListener {
            startActivity(Intent(this, AddActivity::class.java))
        }
        binding.btnKeBookmark.setOnClickListener {
            startActivity(Intent(this, FavoriteActivity::class.java))
        }

        dataMhs()



    }
    override fun onBackPressed() {

        AlertDialog.Builder(this)
            .setTitle("Tutup Aplikasi")
            .setMessage("Yakin tutup dari aplikasi?")
            .setPositiveButton("Ya"){ dialogInterface: DialogInterface, i: Int ->
                finishAffinity()
            }
            .setNegativeButton("Tidak"){ dialogInterface: DialogInterface, i: Int ->
                dialogInterface.dismiss()
            }
            .show()


    }
    fun dataMhs(){

        viewModel = ViewModelProvider(this).get(ViewModelDataMhs::class.java)
        viewModel.getDataMhs().observe(this,{
            if (it != null){
                binding.progressBar.visibility = View.GONE
                binding.rvList.layoutManager = LinearLayoutManager(this,
                    LinearLayoutManager.VERTICAL,false)
                val adapter = MhsAdapter(it)
                binding.rvList.adapter = adapter
                adapter.onDeleteClick ={
                    deleteDataMhs(it.id.toInt())
                }
                adapter.notifyDataSetChanged()
            }
        })
        viewModel.callApiDataMhs()
    }
    fun deleteDataMhs(id : Int){
        viewModel.callDeleteData(id)
        viewModel.getLdDelDataMhs().observe(this,{
            if (it != null){
                dataMhs()
                Toast.makeText(this,"Data Berhasil Dihapus", Toast.LENGTH_SHORT).show()
            }
        })
    }

}