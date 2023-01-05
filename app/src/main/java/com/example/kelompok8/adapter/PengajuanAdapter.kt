package com.example.kelompok8.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kelompok8.PojoModels.ListKP.InternshipItem
import com.example.kelompok8.R
import com.example.kelompok8.databinding.ItemPengajuanBinding
import com.example.kelompok8.models.Pengajuan

class PengajuanAdapter() :

    RecyclerView.Adapter<PengajuanAdapter.pengajuanViewHolder>() {
    private lateinit var pengajuanListener: onClickListener

    var listKP: List<InternshipItem> = ArrayList()

    fun setlistKP(listKP: List<InternshipItem>){
        this.listKP = listKP
        notifyDataSetChanged()
    }

    interface onClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnClickListener(listener: onClickListener) {
        pengajuanListener = listener
    }

    inner class pengajuanViewHolder(val itemBinding: ItemPengajuanBinding, listener: onClickListener):RecyclerView.ViewHolder(itemBinding.root) {
        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): pengajuanViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_pengajuan, parent, false)
        return pengajuanViewHolder(ItemPengajuanBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        pengajuanListener)
    }

    override fun getItemCount(): Int {
        return listKP.size
    }

    override fun onBindViewHolder(holder: pengajuanViewHolder, position: Int) {
        val item: InternshipItem = listKP[position]
        holder.itemBinding.tempat.text = item.agency
    }

}