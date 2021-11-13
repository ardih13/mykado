package com.test.mykado.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.test.mykado.R
import com.test.mykado.data.MenuDashboardModel

class DashboardMenuAdapter(
    val context: Context,
    val mItems: MutableList<MenuDashboardModel>,
    val callback: ActionList
) : RecyclerView.Adapter<DashboardMenuAdapter.Companion.ViewHolder>() {

    companion object {

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val ivLogo: ImageView = itemView.findViewById(R.id.iv_logo)
            val tvTitle: TextView = itemView.findViewById(R.id.tv_title)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_menu_dashboard, parent, false)
        )
    }

    override fun getItemCount(): Int = mItems.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.ivLogo.setImageResource(mItems[position].icon)
        holder.tvTitle.text = mItems[position].label

        holder.ivLogo.setOnClickListener {
            callback.clickItem(position, mItems[position])
        }
    }
}