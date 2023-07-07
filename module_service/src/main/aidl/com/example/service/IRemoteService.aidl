// IRemoteService.aidl
package com.example.service;
import com.example.service.beans.Rect;

// Declare any non-default types here with import statements

interface IRemoteService {
   const int VERSION = 1;
   int getPid();
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

    //Object
    void setRect(in Rect rect);
}