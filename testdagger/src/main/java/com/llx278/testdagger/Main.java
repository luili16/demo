package com.llx278.testdagger;

/**
 * Created by liu on 18-5-25.
 */

public class Main {

    public static void main(String[] args) {

        System.out.println("in Main");

        Client client = new Client();
        Client1 client1 = new Client1();
        //DaggerFactoryComponent.create().inject(client);
        FactoryComponent component = DaggerFactoryComponent.builder().build();
        component.test(client);
        component.injectTo(client1);
        client.print();
        client1.print();

    }

}
