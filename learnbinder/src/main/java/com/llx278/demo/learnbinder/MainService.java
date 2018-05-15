package com.llx278.demo.learnbinder;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by llx on 2018/4/11.
 */

public class MainService extends Service{

    private Binder mBinder = new IMyTest.Stub() {
        @Override
        public String basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {
            Log.d("main","执行basicType方法 当前进程id : " + Process.myPid());
            return "hello";
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}
