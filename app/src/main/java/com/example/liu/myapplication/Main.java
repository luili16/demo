package com.example.liu.myapplication;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class Main {

    public static void main(String[] args) {

        OkHttpClient client = new OkHttpClient();

        RequestBody body = null;
        try {
            /*body = new FormBody.Builder().
                    add("version", URLEncoder.encode("1.0","utf-8")).
                    add("app_ver", URLEncoder.encode("5.1","utf-8")).
                    add("model", URLEncoder.encode("android|ath-t100h","utf-8")).
                    add("macAddress", URLEncoder.encode("24:df:6a:8e:6e:4c","utf-8")).
                    add("channelID", URLEncoder.encode("1","utf-8")).
                    add("scal_height", URLEncoder.encode("1776","utf-8")).
                    add("scal_width", URLEncoder.encode("1080","utf-8")).
                    add("user_id", URLEncoder.encode("0","utf-8")).
                    add("dev", "0").
                    add("encryptedValue", "ce91b4eb333e112d0bb5f43e82c9390a").
                    build();*/
            body = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded"),"version=1.0&app_ver=5.1&model=android%7Cath-tl00h&macAddress=24%3Adf%3A6a%3A8e%3A6e%3A4c&channelID=1&scal_height=1776&scal_width=1080&user_id=0&dev=0&encryptedValue=ce91b4eb333e112d0bb" +
                    "5f43e82c9390a");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Request request = new Request.Builder().
                //addHeader("Content-Type","application/x-www-form-urlencoded").
                //addHeader("Content-Length","197").
                //addHeader("Host","apicng.dili360.com").
                //addHeader("Connection","Keep-Alive").
                //addHeader("Accept-Encoding","gzip").
                //addHeader("User-Agent","okhttp/2.6.0").
                url("http://apicng.dili360.com/cng/index").
                //        url("http://httpbin.org/post").
                        post(body).build();

        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                ResponseBody responseBody = response.body();
                if (responseBody != null) {
                    //responseBody.
                    String str = responseBody.string();

                    System.out.println("body is " + str);
                }
            } else {
                System.out.println("failed!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
