package com.example.liu.myapplication;

import android.app.Service;
import android.content.Intent;
import android.net.LocalServerSocket;
import android.net.LocalSocket;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import android.system.StructPollfd;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 用来做跨进程Socket的服务端
 * Created by liu on 18-4-18.
 */

public class SocketBgService extends Service {

    private static final String TAG = "SocketBgService";
    private ExecutorService mExecutor;

    @Override
    public void onCreate() {
        super.onCreate();
        mExecutor = Executors.newFixedThreadPool(5);
        new Thread() {
            @Override
            public void run() {
                super.run();
                Log.d(TAG, "准备启动LocalServiceSocket");
                try {
                    LocalServerSocket lss = new LocalServerSocket("SocketBgService");
                    FileDescriptor fd = lss.getFileDescriptor();
                    ArrayList<FileDescriptor> fds = new ArrayList<>();
                    ArrayList<Connection> peers = new ArrayList<>();
                    peers.add(null);
                    fds.add(fd);
                    while (true) {
                        StructPollfd[] pollFds = new StructPollfd[fds.size()];
                        for (int i = 0; i < pollFds.length; ++i) {
                            pollFds[i] = new StructPollfd();
                            pollFds[i].fd = fds.get(i);
                            pollFds[i].events = (short) OsConstants.POLLIN;
                        }

                        try {
                            Log.d("main","poll");
                            Os.poll(pollFds, -1);
                        } catch (ErrnoException e) {
                            throw new RuntimeException("poll failed", e);
                        }

                        // 等待一个连接的到来
                        for (int i = pollFds.length - 1; i >= 0; --i) {
                            if ((pollFds[i].revents & OsConstants.POLLIN) == 0) {
                                // 并没有产生一个可以读的数据
                                continue;
                            }

                            // 产生了一个新的socket连接，将新的连接加入到poll调用里面去
                            if (i == 0) {
                                Connection con = new Connection(lss.accept());
                                peers.add(con);
                                fds.add(con.getFileDescriptor());
                            } else {
                                // 原有的socket连接产生了数据
                                Connection con = peers.get(i);
                                con.handle();
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }.start();


    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private class Connection {

        private LocalSocket mLocalSocket;
        private InputStream mInputStream;
        private OutputStream mOutputStream;

        Connection(LocalSocket socket) {
            mLocalSocket = socket;
            try {
                mInputStream = mLocalSocket.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public FileDescriptor getFileDescriptor() {
            return mLocalSocket.getFileDescriptor();
        }

        public void handle() {
            InputStreamReader ips = new InputStreamReader(mInputStream);

            BufferedReader br = new BufferedReader(ips);
            try {
                char[] buff = new char[1024];
                int len = 0;
                StringBuilder sb = new StringBuilder();
                Log.d("main","zhunbeieuqu");
                while ((len = br.read(buff)) != -1) {
                    Log.d("main","读取开始");
                    sb.append(buff,0,len);
                    Log.d("main","读取完成");
                    String str = sb.toString();
                    Log.d("main","temp : " + str);
                }
                String s = sb.toString();
                Log.d("main","s = " + s);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
