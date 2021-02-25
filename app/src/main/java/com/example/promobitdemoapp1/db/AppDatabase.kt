package com.example.promobitdemoapp1.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.promobitdemoapp1.db.dao.DataDao
import com.example.promobitdemoapp1.db.entity.DataEntity

@Database(
    version = DBConstants.DATABASE_VERSION,
    exportSchema = false,
    entities = [DataEntity::class]
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun databaseDao(): DataDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getDatabaseInstance(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context, AppDatabase::class.java, DBConstants.DATABASE_NAME
                    ).allowMainThreadQueries().build()
                }
            }
            return INSTANCE
        }
    }
}