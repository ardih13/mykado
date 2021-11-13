package com.test.mykado.ui.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.test.mykado.R
import com.test.mykado.data.GenderModel
import com.test.mykado.data.RegistrationModel
import com.test.mykado.data.room.CallRoomRegistration
import com.test.mykado.ui.adapter.GenderAdapter
import com.test.mykado.ui.dialog.SuccessRegistrationDialog
import com.test.mykado.util.HtmlUtils
import kotlinx.android.synthetic.main.activity_registration.*
import kotlinx.android.synthetic.main.toolbar_main.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class RegistrationActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener,
    SuccessRegistrationDialog.IRegistration {

    private var doctorName: String? = null
    private var doctorSpesialis: String? = null
    private var doctorJadwal: String? = null

    private var listData = RegistrationModel()
    private var insert = RegistrationModel()

    private val listGender = ArrayList<GenderModel>()
    private var chooseGender = ""

    var isDOB: DatePickerDialog? = null
    var isDate = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        setupUI()
        onSpinnerGender()
        initDatePicker()
        validationButton()
    }

    @SuppressLint("SetTextI18n")
    private fun setupUI() {
        addTextWatcher(etNama)
        addTextWatcher(etTempatLahir)
        addTextWatcher(etTanggalLahir)
        addTextWatcher(etNoHandphone)
        addTextWatcher(etPekerjaan)
        addTextWatcher(etPenanggungJawab)
        addTextWatcher(etAlamat)
        addTextWatcher(etDokter)

        tvTitle.text = "Registrasi Layanan"
        tvNama.text = HtmlUtils.getHTMLContent(getString(R.string.nama_pasien))
        tvGender.text = HtmlUtils.getHTMLContent(getString(R.string.jenis_kelamin))
        tvTempatLahir.text = HtmlUtils.getHTMLContent(getString(R.string.tempat_lahir))
        tvTanggalLahir.text = HtmlUtils.getHTMLContent(getString(R.string.tanggal_lahir))
        tvNoHandphone.text = HtmlUtils.getHTMLContent(getString(R.string.no_handphone_whatsapp))
        tvPekerjaan.text = HtmlUtils.getHTMLContent(getString(R.string.pekerjaan))
        tvPenanggungJawab.text = HtmlUtils.getHTMLContent(getString(R.string.penanggung_jawab))
        tvAlamat.text = HtmlUtils.getHTMLContent(getString(R.string.alamat))
        tvDokter.text = HtmlUtils.getHTMLContent(getString(R.string.pilih_dokter))


        ivBack.setOnClickListener {
            onBackPressed()
        }

        etDokter.setOnClickListener {
            val intent = Intent(this, DoctorActivity::class.java)
            intent.putExtra(DoctorActivity.KEY_FROM_REGISTRASI, true)
            startActivityForResult(intent, 100)
        }

        etTanggalLahir.setOnClickListener {
            isDOB?.show()
        }

        btnRegistrasi.setOnClickListener {
            if (btnRegistrasi.isEnabled) {
                insertToOfflineDatabase()
                val dialog =
                    SuccessRegistrationDialog(this, R.style.AppBottomSheetDialogTheme, this)
                dialog.show()
            } else {
                Toast.makeText(this, "Data tidak valid", Toast.LENGTH_SHORT).show()
            }
        }

    }


    private fun insertToOfflineDatabase() {
        insert = RegistrationModel()
        insert.name = etNama.text.toString()
        insert.gender = chooseGender
        insert.tempat_lahir = etTempatLahir.text.toString()
        insert.tanggal_lahir = etTanggalLahir.text.toString()
        insert.no_handphone = etNoHandphone.text.toString()
        insert.pekerjaan = etPekerjaan.text.toString()
        insert.penanggung_jawab = etPenanggungJawab.text.toString()
        insert.alamat = etAlamat.text.toString()
        insert.dokter_name = doctorName
        insert.dokter_spesialis = doctorSpesialis
        insert.dokter_jadwal = doctorJadwal
        insert.date = getDateNow()

        //listData.add(insert)
        CallRoomRegistration.insertRegistrationLocal(applicationContext, insert)
    }

    fun getDateNow(): String {
        val dateFormatter: DateFormat = SimpleDateFormat("yyyy/MM/dd hh:mm:ss")
        dateFormatter.isLenient = false
        val today = Date()
        val s: String = dateFormatter.format(today)
        return s
    }

    private fun onSpinnerGender() {
        listGender.addAll(
            mutableListOf(
                GenderModel(0, "- Pilih Jenis Kelamin -"),
                GenderModel(1, "Laki-Laki"),
                GenderModel(1, "Perempuan"),
            )
        )

        val spinnerAdapter = GenderAdapter(this, listGender)
        spinnerGender.adapter = spinnerAdapter

        spinnerGender.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                chooseGender = parent.getItemAtPosition(position).toString()
                validationButton()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == Activity.RESULT_OK) {
            val dataExtras = data!!.extras

            if (dataExtras != null) {
                doctorName = dataExtras.getString(DoctorActivity.KEY_DOCTOR_NAME)
                doctorSpesialis = dataExtras.getString(DoctorActivity.KEY_DOCTOR_SPESIALIS)
                doctorJadwal = dataExtras.getString(DoctorActivity.KEY_DOCTOR_JADWAL)
                etDokter.setText(doctorName)
            }
        }
    }

    private fun initDatePicker() {
        val myCalendar = Calendar.getInstance()

        isDOB = DatePickerDialog(
            this,
            this,
            myCalendar[Calendar.YEAR],
            myCalendar[Calendar.MONTH],
            myCalendar[Calendar.DAY_OF_MONTH]
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        isDate = "$dayOfMonth-$month-$year"
        etTanggalLahir.setText(isDate)
        validationButton()
    }

    private fun addTextWatcher(input: EditText) {
        input.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun afterTextChanged(editable: Editable) {
                validationButton()
            }
        })
    }


    private fun validationButton() {
        if (etNama.text.isNotEmpty() && chooseGender != "- Pilih Jenis Kelamin -" && etTempatLahir.text.isNotEmpty()
            && etTanggalLahir.text.isNotEmpty() && etNoHandphone.text.isNotEmpty()
            && etPekerjaan.text.isNotEmpty() && etPenanggungJawab.text.isNotEmpty()
            && etAlamat.text.isNotEmpty() && etDokter.text.isNotEmpty()
        ) {

            btnRegistrasi.isEnabled = true
            btnRegistrasi.background =
                ContextCompat.getDrawable(this, R.drawable.button_purple_round)
        } else {
            btnRegistrasi.isEnabled = false
            btnRegistrasi.background = ContextCompat.getDrawable(this, R.drawable.button_grey_round)
        }
    }

    override fun onSuccessRegistration() {
        val intent = Intent(this, DetailHistoryActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        intent.putExtra(DetailHistoryActivity.KEY_FROM_REGISTRATION, true)
        intent.putExtra(DetailHistoryActivity.KEY_DATA_REGISTRATION, insert)
        startActivity(intent)
    }


}