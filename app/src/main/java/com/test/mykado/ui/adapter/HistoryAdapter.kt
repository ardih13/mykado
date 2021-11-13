package com.test.mykado.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.mykado.R
import com.test.mykado.data.RegistrationModel
import kotlinx.android.synthetic.main.list_item_history.view.*

class HistoryAdapter(
    val context: Context,
    var listData: ArrayList<RegistrationModel>,
    private val callback: ActionList
) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.list_item_history, p0, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listData[position]

        holder.date.text = data.date
        holder.pasien.text = "Pasien : " + data.name
        holder.dokter.text = "Dokter : " + data.dokter_name
        holder.spesialis.text = data.dokter_spesialis

        holder.itemView.setOnClickListener {
            callback.clickItem(position, listData[position])
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var date = itemView.tvDate
        var pasien = itemView.tvNamaPasien
        var dokter = itemView.tvNamaDokter
        var spesialis = itemView.tvSpesialis
    }


}