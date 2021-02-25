package com.example.promobitdemoapp1.db.repository

import android.database.sqlite.SQLiteConstraintException
import android.util.Log
import com.example.promobitdemoapp1.db.AppExecutors
import com.example.promobitdemoapp1.db.dao.DataDao
import com.example.promobitdemoapp1.db.entity.DataEntity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class DatabaseRepository(private val dao: DataDao) {

    fun saveData( dataEntity: DataEntity){
          AppExecutors.instance?.diskIO()?.execute{
              saveDataInDB(dataEntity)
          }
    }
    private fun saveDataInDB(dataEntity: DataEntity):Long{
        try {
            return this.dao.saveData(dataEntity)
        }catch (e : SQLiteConstraintException){
            Log.e("DatabaseRepository","Error occurred while saving data in DB", e)
            return -1
        }
    }

    fun fetchData(id : String):Observable<DataEntity>{
      val observable = Observable.create<DataEntity>{
          emitter ->  Observable.just(dao)
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .flatMap { Observable.just(it.getData(id)) }
          .subscribeOn(AndroidSchedulers.mainThread())
          .subscribe ({
              Log.d("DatabaseRepository","Fetched data : $it")
              emitter.onNext(it)},
              {onError -> Log.e ("DatabaseRepository", "Error occurred while fetchinf data", onError)})
      }
        return observable
    }

    fun ifDataAvailable(id:String): Boolean {
        return this.dao.isDataAvailable(id)
    }

    fun deleteDataWithID(id:String){
        AppExecutors.instance?.diskIO()?.execute {
            this.dao.deleteData()
        }
    }

}