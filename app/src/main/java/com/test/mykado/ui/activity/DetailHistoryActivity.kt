package com.test.mykado.ui.activity

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.test.mykado.BuildConfig
import com.test.mykado.R
import com.test.mykado.data.RegistrationModel
import com.test.mykado.util.FileUtil
import com.test.mykado.util.IConfig
import com.test.mykado.util.ScreenshotUtil
import kotlinx.android.synthetic.main.activity_detail_history.*
import kotlinx.android.synthetic.main.partial_detail.*
import kotlinx.android.synthetic.main.toolbar_detail.*
import java.io.File

class DetailHistoryActivity : AppCompatActivity() {

    private var authority: String? = null
    private var fileRegistration: Bitmap? = null
    private var idRegistration = 0
    private var dataRegistration = RegistrationModel()

    companion object {
        const val KEY_DATA_REGISTRATION = "key_data_regis"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_history)
        authority = BuildConfig.APPLICATION_ID

        getDataIntent()
        setupUI()
    }

    private fun getDataIntent() {
        intent?.extras?.let {
            if (it.containsKey(KEY_DATA_REGISTRATION)) {
                dataRegistration = it.getSerializable(KEY_DATA_REGISTRATION) as RegistrationModel
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setupUI() {
        tvTitle.text = "Detail"

        ivBack.setOnClickListener {
            onBackPressed()
        }

        ivShare.setOnClickListener {
            shareDetailRegistration()
        }

        tvTanggalDaftar.text = dataRegistration.date
        tvNama.text = dataRegistration.name
        tvGender.text = dataRegistration.gender
        tvTempatLahir.text = dataRegistration.tempat_lahir
        tvTanggalLahir.text = dataRegistration.tanggal_lahir
        tvNoHandphone.text = dataRegistration.no_handphone
        tvPekerjaan.text = dataRegistration.pekerjaan
        tvPenanggungJawab.text = dataRegistration.penanggung_jawab
        tvAlamat.text = dataRegistration.alamat
        tvDokter.text = dataRegistration.dokter_name
        tvSpesialis.text = dataRegistration.dokter_spesialis
        idRegistration = dataRegistration.id
    }


    private fun shareDetailRegistration() {
        fileRegistration = ScreenshotUtil.instance?.getBitmapFromView(
            view_receipt,
            view_receipt.getChildAt(0).height,
            view_receipt.getChildAt(0).width
        )

        if (fileRegistration != null) {
            val fileName: String =
                IConfig.FILE_NAME_FOTO + idRegistration + IConfig.EXTENSION_FILE_FOTO

            val folderFoto = File(Environment.getExternalStorageDirectory(), IConfig.FOLDER_FOTO)
            if (!folderFoto.exists()) folderFoto.mkdirs()

            val savedPhoto = File(Environment.getExternalStorageDirectory(), fileName)
            FileUtil.instance?.storeBitmap(fileRegistration!!, savedPhoto.path)

            val contentUri = FileProvider.getUriForFile(this, "$authority", savedPhoto)

            val share = Intent(Intent.ACTION_SEND)
            share.type = "*/*"
            share.putExtra(Intent.EXTRA_EMAIL, "")
            share.putExtra(Intent.EXTRA_SUBJECT, "Registration - $idRegistration")
            share.putExtra(Intent.EXTRA_TEXT, idRegistration)
            share.putExtra(Intent.EXTRA_STREAM, contentUri)
            share.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            startActivity(Intent.createChooser(share, "Share Detail Registration"))
        }
    }


}