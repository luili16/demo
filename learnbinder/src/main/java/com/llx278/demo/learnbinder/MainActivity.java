package com.llx278.demo.learnbinder;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private IMyTest myAidlInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bt = findViewById(R.id.send);
        bt.setOnClickListener(this);
        Intent intent = new Intent(MainActivity.this,MainService.class);
        bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.d("main","onServiceCreated process : " + Process.myPid());
                myAidlInterface = IMyTest.Stub.asInterface(service);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.d("main","onServiceDisconnected process : " + Process.myPid());
            }
        }, Context.BIND_AUTO_CREATE);

    }

    @Override
    public void onClick(View v) {
        try {
            myAidlInterface.basicTypes(1,2,false,3.0f,4.0d,"hell");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
