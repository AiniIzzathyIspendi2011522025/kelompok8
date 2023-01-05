package com.example.kelompok8.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kelompok8.PojoModels.LogbooksItem
import com.example.kelompok8.R
import com.example.kelompok8.databinding.ItemLogbookBinding
import com.example.kelompok8.models.Logbook
import java.text.FieldPosition

class LogbookAdapter():
    RecyclerView.Adapter<LogbookAdapter.LogbookViewHolder> () {

    private lateinit var logbookListener: onClickListener

    var listLogbook: List<LogbooksItem> = ArrayList()

    fun setlistLogbook(listLogbook: List<LogbooksItem>){
        this.listLogbook = listLogbook
        notifyDataSetChanged()
    }

    interface onClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnClickListener(listener: onClickListener) {
        logbookListener = listener
    }

    inner class LogbookViewHolder(val itemBinding: ItemLogbookBinding, listener: onClickListener):RecyclerView.ViewHolder(itemBinding.root) {
        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogbookViewHolder {
        return  LogbookViewHolder(ItemLogbookBinding.inflate(LayoutInflater.from(parent.context), parent, false), logbookListener)
    }

    override fun getItemCount(): Int {
        return listLogbook.size
    }

    override fun onBindViewHolder(holder: LogbookViewHolder, position: Int) {
        val item: LogbooksItem = listLogbook[position]
        holder.itemBinding.kegiatan.text = item.activities



    }

}
