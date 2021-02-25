package com.example.promobitdemoapp1.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.promobitdemoapp1.db.DBConstants
import com.example.promobitdemoapp1.db.entity.DataEntity
import retrofit2.http.DELETE

@Dao interface DataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveData(dataEntity: DataEntity) : Long

    @Query("SELECT * FROM " + DBConstants.DATABASE_TABLE + " WHERE id LIKE :id")
    fun getData(id : String):DataEntity

    @Query("DELETE FROM " + DBConstants.DATABASE_TABLE)
    fun deleteData() : Int

    @Query("SELECT EXISTS (SELECT id FROM " + DBConstants.DATABASE_TABLE +" WHERE id LIKE :id)")
    fun isDataAvailable(id : String):Boolean
}