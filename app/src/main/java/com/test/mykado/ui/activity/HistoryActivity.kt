package com.test.mykado.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.mykado.R
import com.test.mykado.data.RegistrationModel
import com.test.mykado.data.room.CallRoomRegistration
import com.test.mykado.ui.adapter.ActionList
import com.test.mykado.ui.adapter.HistoryAdapter
import kotlinx.android.synthetic.main.activity_history.*
import kotlinx.android.synthetic.main.partial_search.*
import kotlinx.android.synthetic.main.toolbar_main.*

class HistoryActivity : AppCompatActivity() {

    private val listRegistration = ArrayList<RegistrationModel>()
    private var adapterHistory: HistoryAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        setupUI()
        getListHistory()
    }

    @SuppressLint("SetTextI18n")
    private fun setupUI() {
        tvTitle.text = "Riwayat"
        etQuery.hint = "Cari Pasien"

        etQuery.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                getListHistorySearching(s.toString())

                if (etQuery.text.toString() != "") {
                    iv_clear.visibility = View.VISIBLE
                } else {
                    iv_clear.visibility = View.GONE
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        iv_clear.setOnClickListener {
            etQuery.setText("")
        }

        ivBack.setOnClickListener {
            onBackToDashboard()
        }
    }

    private fun getListHistory() {

        this.let { CallRoomRegistration.getRegistrationRoom(it) }.let {
            listRegistration.clear()
            listRegistration.addAll(it)
        }

        adapterHistory = HistoryAdapter(this, listRegistration, object : ActionList {
            override fun clickItem(position: Int, value: Any) {
                val items = value as RegistrationModel
                val intent = Intent(this@HistoryActivity, DetailHistoryActivity::class.java)
                intent.putExtra(DetailHistoryActivity.KEY_DATA_REGISTRATION, items)
                startActivity(intent)
            }
        })

        rvHistory?.apply {
            layoutManager = LinearLayoutManager(context)
            itemAnimator = DefaultItemAnimator()
            adapter = adapterHistory
        }
    }

    private fun getListHistorySearching(query: String) {

        this.let { CallRoomRegistration.getRegistrationRoomSearching(it, query) }.let {
            listRegistration.clear()
            listRegistration.addAll(it)
        }

        rvHistory?.apply {
            layoutManager = LinearLayoutManager(context)
            itemAnimator = DefaultItemAnimator()
            adapter = adapterHistory
        }
    }


    override fun onBackPressed() {
        onBackToDashboard()
    }

    private fun onBackToDashboard() {
        val intent = Intent(this, DashboardActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}