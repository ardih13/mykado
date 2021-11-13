package com.test.mykado.data.room

import androidx.room.*

@Dao
interface RegistrationDao {

    @Insert
    fun insert(registration: Registration)

    @Query("SELECT * FROM `registration` ORDER BY id DESC")
    fun getAllRegistration(): MutableList<Registration>

    @Query("SELECT * FROM `registration` WHERE name like '%' || :qry || '%' ORDER BY id DESC")
    fun getAllRegistrationSearching(qry: String): MutableList<Registration>

}