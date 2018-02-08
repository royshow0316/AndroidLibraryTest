package com.roy.librarytest

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.os.RemoteException
import android.util.Log
import com.roy.service.aidl.IMyService
import com.roy.service.aidl.IMyServiceCallback
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        private val TAG = MainActivity.toString()
    }

    private var mIMyServoce : IMyService? = null
    private var mData = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_start_service.setOnClickListener { onStartService() }
        btn_stop_service.setOnClickListener { onStopService() }
        btn_bind_service.setOnClickListener { onBindService() }
        btn_unbind_service.setOnClickListener { onUnBindService() }
        btn_call_service_callback.setOnClickListener { onCallServiceCallback() }
    }

    private val mCallback = object : IMyServiceCallback.Stub() {
        override fun onCallback(data: Int) {
            try {
                Log.d(TAG, "onCallback() = " + data)
            } catch (e: RemoteException) {
                Log.d(TAG, e.toString())
            }
        }
    }

    private val mServiceConnection = object : ServiceConnection {
        @Throws(RemoteException::class)
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            Log.d(TAG, "onServiceConnected()")
            mIMyServoce = IMyService.Stub.asInterface(service)
            mIMyServoce!!.registerCallback(mCallback)
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            Log.d(TAG, "onServiceDisconnected()")
            mIMyServoce = null
        }
    }

    private fun onStartService() {
        Log.d(TAG, "onStartService()")
        mIMyServoce = null
        val intent = Intent().setClassName(Constants.SERVICE_PACKAGE_NAME, Constants.VOICE_SERVICE_PACKAGE_NAME)
        startService(intent)
    }

    private fun onStopService() {
        Log.d(TAG, "onStopService()")
        mIMyServoce = null
        val intent = Intent().setClassName(Constants.SERVICE_PACKAGE_NAME, Constants.VOICE_SERVICE_PACKAGE_NAME)
        stopService(intent)
    }

    private fun onBindService() {
        Log.d(TAG, "onBindService()")
        mIMyServoce = null
        val intent = Intent().setClassName(Constants.SERVICE_PACKAGE_NAME, Constants.VOICE_SERVICE_PACKAGE_NAME)
        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE)
    }

    private fun onUnBindService() {
        Log.d(TAG, "onUnBindService()")
        mIMyServoce = null
        unbindService(mServiceConnection)
    }

    private fun onCallServiceCallback() {
        Log.d(TAG, "onCallServiceCallback()")
        if (mIMyServoce != null) {
            try {
                mIMyServoce!!.callService(mData)
            } catch (e: RemoteException) {
                Log.d(TAG, e.toString())
            }
            mData++
        }
    }
}
