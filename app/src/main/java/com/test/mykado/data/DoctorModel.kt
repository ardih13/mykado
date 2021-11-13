package com.test.mykado.data

import java.io.Serializable

class DoctorModel : Serializable {

    var id: Int = 0
    var name: String = ""
    var spesialis: String = ""
    var poli: String = ""
    var jadwal: String = ""

    constructor(id: Int, name: String, spesialis: String, poli: String, jadwal: String) {
        this.id = id
        this.name = name
        this.spesialis = spesialis
        this.poli = poli
        this.jadwal = jadwal
    }
}