package com.example.chap6top5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chap6top5.databinding.ActivityMainBinding
import com.example.chap6top5.view.MakeupAdapter
import com.example.chap6top5.viewmodel.ViewModelMakeup
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    lateinit var viewModel : ViewModelMakeup
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getDataMakeup()

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