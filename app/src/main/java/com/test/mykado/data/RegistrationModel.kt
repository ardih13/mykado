package com.test.mykado.data

import java.io.Serializable

class RegistrationModel : Serializable {

    var id: Int = 0
    var name: String? = null
    var gender: String? = null
    var tempat_lahir: String? = null
    var tanggal_lahir: String? = null
    var no_handphone: String? = null
    var pekerjaan: String? = null
    var penanggung_jawab: String? = null
    var alamat: String? = null
    var dokter_name: String? = null
    var dokter_spesialis: String? = null
    var dokter_jadwal: String? = null
    var date: String? = null

}