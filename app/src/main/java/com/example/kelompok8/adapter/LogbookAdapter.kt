package com.example.kelompok8.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kelompok8.R
import com.example.kelompok8.models.Logbook
import java.text.FieldPosition

class LogbookAdapter (private var logbookList : ArrayList<Logbook>) :
    RecyclerView.Adapter<LogbookAdapter.logbookViewHolder>() {

    private lateinit var myListener : onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setonItemClickListener(listener:onItemClickListener){
        myListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): logbookViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_logbook, parent, false)
        return logbookViewHolder(itemView, myListener)
    }

    override fun onBindViewHolder(holder: logbookViewHolder, position: Int) {
        val currentPosition = logbookList[position]
        holder.tanggal.setText(currentPosition.tanggal)
    }

    override fun getItemCount(): Int {
        return logbookList.size
    }

    class logbookViewHolder (itemView: View, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView){
        val tanggal : TextView = itemView.findViewById(R.id.tanggal)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

}