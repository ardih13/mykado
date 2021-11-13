package com.test.mykado.data.room

import android.content.Context
import com.test.mykado.data.OfflineDatabase
import com.test.mykado.data.RegistrationModel

object CallRoomRegistration {

    private fun initRoomDB(ctx: Context): OfflineDatabase {
        return OfflineDatabase.getDatabase(ctx)
    }

    private fun callRegistrationDao(ctx: Context): RegistrationDao {
        val dao = initRoomDB(ctx).getRegistrationDao()
        return dao
    }

    private fun convertFromInsertRegistrationLocal(regis: RegistrationModel): Registration {
        return Registration(
            0,
            regis.name,
            regis.gender,
            regis.tempat_lahir,
            regis.tanggal_lahir,
            regis.no_handphone,
            regis.pekerjaan,
            regis.penanggung_jawab,
            regis.alamat,
            regis.dokter_name,
            regis.dokter_spesialis,
            regis.dokter_jadwal,
            regis.date,
        )
    }

    fun insertRegistrationLocal(ctx: Context, listRegistration: MutableList<RegistrationModel>) {
        for (i in 0 until listRegistration.size) {
            val transaction = convertFromInsertRegistrationLocal(listRegistration.get(i))
            callRegistrationDao(ctx).insert(transaction)
        }
    }

    //get data from local
    private fun convertFromInsertRegistrationResponseModel(regis: Registration): RegistrationModel {
        val transactionItem = RegistrationModel()
        transactionItem.id = regis.id
        transactionItem.name = regis.name
        transactionItem.gender = regis.gender
        transactionItem.tempat_lahir = regis.tempat_lahir
        transactionItem.tanggal_lahir = regis.tanggal_lahir
        transactionItem.no_handphone = regis.no_handphone
        transactionItem.pekerjaan = regis.pekerjaan
        transactionItem.penanggung_jawab = regis.penanggung_jawab
        transactionItem.alamat = regis.alamat
        transactionItem.dokter_name = regis.dokter_name
        transactionItem.dokter_spesialis = regis.dokter_spesialis
        transactionItem.dokter_jadwal = regis.dokter_jadwal
        transactionItem.date = regis.date

        return transactionItem
    }


    fun getRegistrationRoom(ctx: Context): MutableList<RegistrationModel> {
        val registrationList = callRegistrationDao(ctx).getAllRegistration()
        val registrationItemList = mutableListOf<RegistrationModel>()

        for (i in 0 until registrationList.size) {
            registrationItemList.add(convertFromInsertRegistrationResponseModel(registrationList.get(i)))
        }
        return registrationItemList
    }

    fun getRegistrationRoomSearching(ctx: Context, query: String): MutableList<RegistrationModel> {
        val registrationList = callRegistrationDao(ctx).getAllRegistrationSearching(query)
        val registrationItemList = mutableListOf<RegistrationModel>()

        for (i in 0 until registrationList.size) {
            registrationItemList.add(convertFromInsertRegistrationResponseModel(registrationList.get(i)))
        }
        return registrationItemList
    }

}