package com.example.kelompok8.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kelompok8.PojoModels.PengajuanResponse
import com.example.kelompok8.R
import com.example.kelompok8.models.Pengajuan
import kotlinx.android.synthetic.main.activity_list_pengajuan_kp.view.*
import kotlinx.android.synthetic.main.item_pengajuan.view.*

class PengajuanAdapter(private val list: ArrayList<Pengajuan>): RecyclerView.Adapter<>(PengajuanAdapter) {

    inner class PengajuanViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(pengajuanResponse: PengajuanResponse){
        with(itemView){
            val text ="id : $pengajuanResponse.id}\n" +
                    "title : ${pengajuanResponse}\n" +
                    "text : ${pengajuanResponse}"
            tvText.text = text
        }
        }
    }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PengajuanViewHolder{
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pengajuan, parent, false)
            return PengajuanViewHolder(view)
        }

        override fun getItemCount() : Int = list.size

        override fun onBindViewHolder(holder: PengajuanViewHolder, position: Int) {
            holder.bind(list[position])
//

                    }
                }