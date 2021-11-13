package com.test.mykado.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "registration")
data class Registration(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Int = 0,
    @ColumnInfo(name = "name") var name: String? = "",
    @ColumnInfo(name = "gender") var gender: String? = "",
    @ColumnInfo(name = "tempat_lahir") var tempat_lahir: String? = "",
    @ColumnInfo(name = "tanggal_lahir") var tanggal_lahir: String? = "",
    @ColumnInfo(name = "no_handphone") var no_handphone: String? = "",
    @ColumnInfo(name = "pekerjaan") var pekerjaan: String? = "",
    @ColumnInfo(name = "penanggung_jawab") var penanggung_jawab: String? = "",
    @ColumnInfo(name = "alamat") var alamat: String? = "",
    @ColumnInfo(name = "dokter_name") var dokter_name: String? = "",
    @ColumnInfo(name = "dokter_spesialis") var dokter_spesialis: String? = "",
    @ColumnInfo(name = "dokter_jadwal") var dokter_jadwal: String? = "",
    @ColumnInfo(name = "date") var date: String? = "",

    ) : Serializable