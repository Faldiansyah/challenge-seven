package com.example.chap6top5.view

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chap6top5.databinding.ItemBinding
import com.example.chap6top5.model.ResponseDataMhsItem

class MhsAdapter(var listData : List<ResponseDataMhsItem>): RecyclerView.Adapter<MhsAdapter.ViewHolder>() {
    var onDeleteClick : ((ResponseDataMhsItem)-> Unit)? = null

    class ViewHolder(var binding : ItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view= ItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.txtNama.text =   ("Nama               : "+listData[position].nama)
        holder.binding.txtNim.text =    ("Nim                   : "+listData[position].nim)
        holder.binding.txtJk.text =     ("Jenis Kelamin : "+listData[position].jk)
        holder.binding.txtAlamat.text = ("Alamat              : "+listData[position].alamat)

        Glide.with(holder.itemView).load(listData[position].foto).circleCrop().into(holder.binding.imgMhs)

        holder.binding.item.setOnClickListener {
            var detail = Intent(it.context, DetailActivity::class.java)
            detail.putExtra("id",listData[position].id)
            it.context.startActivity(detail)
        }

        holder.binding.imgEdit.setOnClickListener {
            var edit = Intent(it.context, EditDataActivity::class.java)
            edit.putExtra("id",listData[position].id)
            it.context.startActivity(edit)
        }

        holder.binding.imgBm.setOnClickListener {
            var bm = Intent(it.context, BookmarkActivity::class.java)
            bm.putExtra("id",listData[position].id)
            it.context.startActivity(bm)
        }

        holder.binding.imgDelete.setOnClickListener {
            onDeleteClick?.invoke(listData[position])
        }
    }

    override fun getItemCount(): Int {
        return listData.size
    }
}