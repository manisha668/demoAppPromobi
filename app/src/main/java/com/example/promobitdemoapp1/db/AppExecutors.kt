package com.example.promobitdemoapp1.db

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.Executors


class AppExecutors private constructor(
    private val diskIO : Executor,
    private val networkIO : Executor,
    private val mainThread : Executor
){
    fun diskIO():Executor{
        return diskIO
    }
    fun mainThread():Executor{
      return mainThread
    }
    fun networkIO():Executor{
        return networkIO
    }
    private class MainThreadExecutor : Executor{
        private val mainThreadHandler = Handler(Looper.getMainLooper())
        override fun execute(command: Runnable) {
              mainThreadHandler.post(command)
        }
    }

    companion object{
     private val LOCK = Any()
        private var mInstance: AppExecutors? = null
        val instance : AppExecutors?
        get() {
            if(mInstance == null){
                synchronized(LOCK){
                    mInstance = AppExecutors(
                        Executors.newSingleThreadExecutor(),
                        Executors.newFixedThreadPool(3),
                        MainThreadExecutor()
                    )
                }
            }
            return mInstance
        }
    }
}