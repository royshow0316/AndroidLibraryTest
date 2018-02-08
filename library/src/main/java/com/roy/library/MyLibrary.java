package com.roy.library;

import android.content.Context;
import android.util.Log;

/**
 * Created by roy on 2018/2/8.
 */

public class MyLibrary {
    private static final String TAG = "MyLibrary";

    private Context mContext;

    public interface MyLibraryCallback {
        void onConnected(boolean isConnected);
    }

    public MyLibrary(Context context) {
        Log.d(TAG, "VoiceSDK");
        this.mContext = context;
    }

    public String sayHi(String name) {
        return "Hello " + name;
    }
}
