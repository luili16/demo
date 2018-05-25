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
import coffee.DaggerCoffeeApp_CoffeeShop;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener {

    private OutputStream mOps;
    private LocalSocket localSocket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String android_socket_zygote = System.getenv("ANDROID_SOCKET_zygote");
        Log.d("main", "env is : " + android_socket_zygote);

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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start:
                Intent service = new Intent(this, SocketBgService.class);
                startService(service);
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
