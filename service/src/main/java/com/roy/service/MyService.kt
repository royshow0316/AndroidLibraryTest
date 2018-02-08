package com.roy.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.RemoteException
import android.util.Log
import com.roy.service.aidl.IMyService
import com.roy.service.aidl.IMyServiceCallback

/**
 * Created by roy on 2018/2/8.
 */

class MyService : Service() {
    companion object {
        private val TAG = "MyService"
    }

    private var mCallback : IMyServiceCallback? = null

    private val mBinder = object : IMyService.Stub() {
        @Throws(RemoteException::class)
        override fun registerCallback(callback: IMyServiceCallback?) {
            Log.d(TAG, "registerCallback()")
            mCallback = callback
        }

        @Throws(RemoteException::class)
        override fun unregisterCallback(callback: IMyServiceCallback?) {
            Log.d(TAG, "unregisterCallback")
        }

        @Throws(RemoteException::class)
        override fun callService(data: Int) {
            Log.d(TAG, "callService() = " + data)
            if (mCallback != null) {
                mCallback!!.onCallback(data)
            }
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        Log.d(TAG, "onBind()")
        return mBinder
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate()")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand()")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.d(TAG, "onUnbind()")
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy()")
    }
}