package com.llx278.testdagger;

/**
 * Created by liu on 18-5-25.
 */

public class Main {

    public static void main(String[] args) {

        System.out.println("in Main");

        Client client = new Client();
        //DaggerFactoryComponent.create().inject(client);
        DaggerFactoryComponent.builder().build().test(client);
        client.print();

    }

}
