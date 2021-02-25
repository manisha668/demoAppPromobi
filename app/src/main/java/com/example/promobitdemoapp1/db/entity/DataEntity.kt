package com.example.promobitdemoapp1.db.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.promobitdemoapp1.db.DBConstants

@Entity(tableName = DBConstants.DATABASE_TABLE)
data class DataEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo
    var id : String,
    @ColumnInfo
    var data : String
)
