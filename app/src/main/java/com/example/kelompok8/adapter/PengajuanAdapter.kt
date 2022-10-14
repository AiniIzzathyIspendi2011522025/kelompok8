package com.example.kelompok8.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kelompok8.R
import com.example.kelompok8.models.Pengajuan

class PengajuanAdapter(private val tempatList : ArrayList<Pengajuan>) :

    RecyclerView.Adapter<PengajuanAdapter.pengajuanViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): pengajuanViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_pengajuan, parent, false)
        return PengajuanAdapter.pengajuanViewHolder(itemView)
    }


    override fun getItemCount(): Int {
        return tempatList.size
    }

    class pengajuanViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tempat: TextView = itemView.findViewById(R.id.tempat)


    }

    override fun onBindViewHolder(holder: pengajuanViewHolder, position: Int) {
        val currentPosition = tempatList[position]
        holder.tempat.setText(currentPosition.tempat)
    }

}