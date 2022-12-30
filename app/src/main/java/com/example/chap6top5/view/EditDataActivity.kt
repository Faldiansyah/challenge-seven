package com.example.chap6top5.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.chap6top5.MainActivity
import com.example.chap6top5.R
import com.example.chap6top5.databinding.ActivityEditDataBinding
import com.example.chap6top5.viewmodel.ViewModelDataMhs
import dagger.hilt.android.AndroidEntryPoint
import kotlin.properties.Delegates

@AndroidEntryPoint
class EditDataActivity : AppCompatActivity() {
    lateinit var binding : ActivityEditDataBinding
    lateinit var viewModel : ViewModelDataMhs

    var id by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(ViewModelDataMhs::class.java)
        id = intent.getStringExtra("id")!!.toInt()

        setDataToInput()

        binding.btnSimpanData.setOnClickListener {
            editData()
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            Toast.makeText(this,"Data Berhasil Di Edit", Toast.LENGTH_SHORT).show()
        }

    }

    fun setDataToInput(){
        viewModel.callGetDataMhs(id)
        viewModel.getDataMhs(id).observe(this,{
            binding.editNama.setText(it!!.nama)
            binding.editNim.setText(it.nim)
            binding.editAlamat.setText(it.alamat)
            binding.editFoto.setText(it.foto)
            binding.editJk.setText(it.jk)
        })

    }

    fun editData(){
        var nama = binding.editNama.text.toString()
        var nim = binding.editNim.text.toString()
        var alamat = binding.editAlamat.text.toString()
        var foto = binding.editFoto.text.toString()
        var jk = binding.editJk.text.toString()

        viewModel.editApiDataMhs(id,nama,nim,alamat,foto,jk)
        viewModel.editDataMhs().observe(this,{
            if (it != null){
                Toast.makeText(this,"Data Berhasil Di Edit", Toast.LENGTH_SHORT).show()
            }
        })

    }
}