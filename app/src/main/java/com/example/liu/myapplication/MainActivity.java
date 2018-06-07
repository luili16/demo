package com.example.liu.myapplication;

import android.content.Intent;
import android.net.LocalSocket;
import android.net.LocalSocketAddress;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.io.OutputStream;

import coffee.CoffeeApp;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener {

    private OutputStream mOps;
    private LocalSocket localSocket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("main","onCreate");
        String android_socket_zygote = System.getenv("ANDROID_SOCKET_zygote");
        Button start = findViewById(R.id.start);
        start.setOnClickListener(this);
        Button connect = findViewById(R.id.connect);
        connect.setOnClickListener(this);
        Button send = findViewById(R.id.send);
        send.setOnClickListener(this);


        //CoffeeApp.CoffeeShop coffeeShop = DaggerCoffeeApp_CoffeeShop.builder().build();
        //coffeeShop.maker().brew();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("main","onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("main","OnResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("main","onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("main","onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("main","onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("main","onRestart");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("main","onSaveInstanceState");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start:
                Intent intent = new Intent(getApplicationContext(),TranslucentActivity.class);
                startActivity(intent);
                break;
            case R.id.connect:
                new Thread() {
                    @Override
                    public void run() {
                        super.run();

                        Log.d("main", "创建localSocket");
                        localSocket = new LocalSocket();
                        LocalSocketAddress address = new LocalSocketAddress("SocketBgService");
                        try {
                            localSocket.connect(address);
                            mOps = localSocket.getOutputStream();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }.start();
                break;
            case R.id.send:
                new Thread() {
                    @Override
                    public void run() {
                        super.run();

                        Log.d("main", "发送数据");
                        try {
                            mOps.write("helloworld\n".getBytes());
                            mOps.flush();
                            localSocket.shutdownOutput();
                            localSocket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                    }
                }.start();
                break;
        }
    }
}
