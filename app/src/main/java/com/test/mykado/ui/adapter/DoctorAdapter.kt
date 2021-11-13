package com.test.mykado.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.test.mykado.R
import com.test.mykado.data.DoctorModel

class DoctorAdapter(var mItems: ArrayList<DoctorModel>, private val callback: ActionList) :
    RecyclerView.Adapter<DoctorAdapter.Companion.ViewHolder>() {

    companion object {

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val doctorName: TextView = itemView.findViewById(R.id.tvDoctorName)
            val spesialis: TextView = itemView.findViewById(R.id.tvSpesialis)
            val poli: TextView = itemView.findViewById(R.id.tvPoli)
            val jadwal: TextView = itemView.findViewById(R.id.tvJadwal)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_doctor, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = mItems[position]

        holder.doctorName.text = data.name
        holder.spesialis.text = data.spesialis
        holder.poli.text = data.poli
        holder.jadwal.text = data.jadwal

        holder.itemView.setOnClickListener {
            callback.clickItem(position, mItems[position])
        }
    }

    //search
    @SuppressLint("NotifyDataSetChanged")
    fun searchList(listData: ArrayList<DoctorModel>) {
        mItems = listData
        notifyDataSetChanged()
    }
}