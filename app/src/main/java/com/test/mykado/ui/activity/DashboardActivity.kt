package com.test.mykado.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.test.mykado.R
import com.test.mykado.data.MenuDashboardModel
import com.test.mykado.ui.adapter.ActionList
import com.test.mykado.ui.adapter.DashboardMenuAdapter
import kotlinx.android.synthetic.main.activity_dashboard.*
import java.util.*

class DashboardActivity : BaseLocalActivity() {

    private val mItemMenu = mutableListOf<MenuDashboardModel>()
    private var adapterMenu: DashboardMenuAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        //initPermission()
        configureMenuDashboard()
    }

    private fun configureMenuDashboard() {

        mItemMenu.addAll(
            mutableListOf(
                MenuDashboardModel(
                    R.id.menu_registration,
                    R.drawable.ic_registration,
                    getString(R.string.menu_registration)
                ),
                MenuDashboardModel(
                    R.id.menu_schedule,
                    R.drawable.ic_schedule,
                    getString(R.string.menu_schedule)
                ),
                MenuDashboardModel(
                    R.id.menu_history,
                    R.drawable.ic_history,
                    getString(R.string.menu_history)
                )
            )
        )

        adapterMenu = DashboardMenuAdapter(this, mItemMenu, object : ActionList {
            override fun clickItem(position: Int, value: Any) {
                val item = value as MenuDashboardModel

                when (item.id) {
                    R.id.menu_registration -> {
                        startActivity(Intent(this@DashboardActivity, RegistrationActivity::class.java))
                    }
                    R.id.menu_schedule -> {
                        startActivity(Intent(this@DashboardActivity, DoctorActivity::class.java))
                    }
                    R.id.menu_history -> {
                        startActivity(Intent(this@DashboardActivity, HistoryActivity::class.java))
                    }
                }

            }
        })

        rvMenuDashboard?.apply {
            layoutManager = GridLayoutManager(this@DashboardActivity, mItemMenu.size)
            itemAnimator = DefaultItemAnimator()
            adapter = adapterMenu
        }
    }
}