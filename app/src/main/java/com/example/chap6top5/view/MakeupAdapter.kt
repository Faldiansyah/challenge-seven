package com.example.chap6top5.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chap6top5.databinding.ItemBinding
import com.example.chap6top5.model.ResponseDataMakeupItem

class MakeupAdapter(var listMakeup : List<ResponseDataMakeupItem>): RecyclerView.Adapter<MakeupAdapter.ViewHolder>() {
    class ViewHolder(var binding : ItemBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return  ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.nameMakeup.text = listMakeup[position].name
        holder.binding.brand.text = listMakeup[position].brand
        holder.binding.priceMakeup.text = listMakeup[position].price

        Glide.with(holder.itemView.context).load(listMakeup[position].imageLink).into(holder.binding.imgProduct)
    }

    override fun getItemCount(): Int {
        return listMakeup.size
    }
}