// IMyService.aidl
package com.roy.service.aidl;

// Declare any non-default types here with import statements
import com.roy.service.aidl.IMyServiceCallback;

interface IMyService {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void registerCallback(IMyServiceCallback callback);
    void unregisterCallback(IMyServiceCallback callback);
    void callService(int data);
}
