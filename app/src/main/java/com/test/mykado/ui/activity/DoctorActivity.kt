package com.test.mykado.ui.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.mykado.R
import com.test.mykado.data.DoctorModel
import com.test.mykado.ui.adapter.ActionList
import com.test.mykado.ui.adapter.DoctorAdapter
import kotlinx.android.synthetic.main.activity_doctor.*
import kotlinx.android.synthetic.main.partial_search.*
import kotlinx.android.synthetic.main.toolbar_main.*

class DoctorActivity : AppCompatActivity() {

    private val listDoctor = ArrayList<DoctorModel>()
    private var adapterDoctor: DoctorAdapter? = null
    private var fromRegistration = false

    companion object {
        const val KEY_FROM_REGISTRASI = "key_registrasi"
        const val KEY_DOCTOR_NAME = "key_name"
        const val KEY_DOCTOR_SPESIALIS = "key_spesialis"
        const val KEY_DOCTOR_JADWAL = "key_jadwal"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor)

        setupUI()
        setDataDoctor()
    }

    @SuppressLint("SetTextI18n")
    private fun setupUI() {
        tvTitle.text = "Dokter"

        ivBack.setOnClickListener {
            onBackPressed()
        }

        intent?.extras?.let {
            if (it.containsKey(KEY_FROM_REGISTRASI)) {
                fromRegistration = it.getBoolean(KEY_FROM_REGISTRASI)
            }
        }

        etQuery.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                searchDoctor(s.toString())

                when {
                    etQuery.text.toString() != "" -> {
                        iv_clear.visibility = View.VISIBLE
                    }
                    else -> {
                        iv_clear.visibility = View.GONE
                    }
                }
            }

        })

        iv_clear.setOnClickListener {
            etQuery.setText("")
        }
    }


    private fun setDataDoctor() {

        listDoctor.addAll(
            mutableListOf(
                DoctorModel(
                    0,
                    "Drg. Muthia Ardhani Sp.KGA",
                    "Spesialis Dokter Gigi",
                    "",
                    "Senin-Rabu (08:00-15:00)"
                ),
                DoctorModel(
                    1,
                    "Dr. Sopyan Hadi Sp.B",
                    "Spesialis Bedah",
                    "",
                    "Selasa-Kamis (09:00-14:00)"
                ),
                DoctorModel(
                    2,
                    "Dr. Arif Adimulya Sp.JP",
                    "Spesialis Jantung",
                    "",
                    "Kamis-Jumat (11:00-17:00"
                ),
                DoctorModel(
                    3,
                    "Dr. Diandra Sabila Giana",
                    "Dokter Umum",
                    "",
                    "Kamis (08:00-17:00)"
                ),
                DoctorModel(
                    4,
                    "Dr. Fitra Kadarsih Sp.KK",
                    "Spesialis Kulit",
                    "",
                    "Senin-Kamis (08:00-16:00)"
                ),
                DoctorModel(
                    5,
                    "Dr. Febria Resti Sp.M",
                    "Spesialis Mata",
                    "",
                    "Kamis-Jumat (17:00-21:00)"
                ),
                DoctorModel(
                    6,
                    "Dr. Tiara Destafia Sp.THT",
                    "Spesialis THT",
                    "",
                    "Senin-Kamis (08:00-15:00)"
                )
            )
        )

        adapterDoctor = DoctorAdapter(listDoctor, object : ActionList {

            override fun clickItem(position: Int, value: Any) {
                val items = value as DoctorModel
                if (fromRegistration) {
                    val intent = Intent()
                    intent.putExtra(KEY_DOCTOR_NAME, items.name)
                    intent.putExtra(KEY_DOCTOR_SPESIALIS, items.spesialis)
                    intent.putExtra(KEY_DOCTOR_JADWAL, items.jadwal)
                    setResult(Activity.RESULT_OK, intent)
                    finish()
                }
            }
        })

        rvDoctor?.apply {
            layoutManager = LinearLayoutManager(context)
            itemAnimator = DefaultItemAnimator()
            adapter = adapterDoctor
        }
    }

    //search
    fun searchDoctor(text: String) {
        val filteredList: ArrayList<DoctorModel> = ArrayList()
        for (item in listDoctor) {
            if (item.name.toLowerCase().contains(text.toLowerCase()) ||
                item.poli.toLowerCase().contains(text.toLowerCase())
            ) {
                filteredList.add(item)
            }
        }
        adapterDoctor?.searchList(filteredList)
    }


}