package com.llx278.testdagger;

import javax.inject.Inject;

/**
 * Created by liu on 18-5-28.
 */

public class Client1 {

    @Inject
    Service mService;

    public void print() {
        mService.print();
    }


    @Inject
    public void injectService1(Service1 service1) {
        service1.print();
    }

}
