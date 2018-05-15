/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.hellojni;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HelloJni extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /* Retrieve our TextView and set its content.
         * the text is retrieved by calling a native
         * function.
         */
        setContentView(R.layout.activity_hello_jni);

        Button bt1 = (Button) findViewById(R.id.bt1);
        bt1.setOnClickListener(this);
        Button bt2 = (Button) findViewById(R.id.bt2);
        bt2.setOnClickListener(this);
    }

    /**
     * 测试jni调用无参数，无返回值的method0
     */
    private void method0() {
        Log.d("main","method0 has been called");
    }

    /**
     * 测试由jni调用method1
     * @param param1 参数1
     * @param param2 参数2
     * @return 测试的String
     */
    public boolean method1(String param1,int param2) {
        Log.d("main","method1 has been called param1 = "
                + param1 + " param2 = " + param2);
        return true;
    }

    public Boolean method2(int param1) {
        Log.d("main","method2 has been called param1 = "
                + param1);
        return true;
    }

    public byte method3(byte param) {
        Log.d("main","method3 has been called param = " + param);
        return param;
    }

    private char method4(char param) {
        Log.d("main","method4 has been called param = " + param);
        return param;
    }

    /* A native method that is implemented by the
     * 'hello-jni' native library, which is packaged
     * with this application.
     */
    public native String  stringFromJNI();

    public native String testParam(int a,float b,String c);

    private native void callJava();

    /* This is another native method declaration that is *not*
     * implemented by 'hello-jni'. This is simply to show that
     * you can declare as many native methods in your Java code
     * as you want, their implementation is searched in the
     * currently loaded native libraries only the first time
     * you call them.
     *
     * Trying to call this function will result in a
     * java.lang.UnsatisfiedLinkError exception !
     */
    public native String  unimplementedStringFromJNI();

    public native String[] myTestFunc(String[] param);

    /* this is used to load the 'hello-jni' library on application
     * startup. The library has already been unpacked into
     * /data/data/com.example.hellojni/lib/libhello-jni.so at
     * installation time by the package manager.
     */
    static {
        System.loadLibrary("hello-jni");
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.bt1:
                callJava();
                break;
            case R.id.bt2:
                myTestFunc(new String[]{"str1","str2"});
            default:
        }

    }
}
