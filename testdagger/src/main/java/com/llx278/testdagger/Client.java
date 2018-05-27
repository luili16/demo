package com.llx278.testdagger;

import javax.inject.Inject;

/**
 * Created by liu on 18-5-25.
 */

public class Client {

    @Inject
    Service mService;

    public Client() {

        //DaggerFactoryComponent.builder().build().inject(this);
    }

    public void print() {
        mService.print();
    }

}
