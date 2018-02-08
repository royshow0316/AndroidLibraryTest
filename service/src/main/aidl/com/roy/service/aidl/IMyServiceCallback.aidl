// IMyServiceCallback.aidl
package com.roy.service.aidl;

// Declare any non-default types here with import statements

interface IMyServiceCallback {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void onCallback(int data);
}
