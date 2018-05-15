package com.example.hellojni;

import android.util.Log;

/**
 * Created by llx on 2018/4/8.
 */

public class Test1 {

    private String mFiled1 = "hello test1";
    private int mFiled2 = -100;

    public void method1(String param1){
        Log.d("main","call Test1.Method1 param1 = " + param1);
    }
}
