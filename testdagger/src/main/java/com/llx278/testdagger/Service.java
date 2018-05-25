package com.llx278.testdagger;

import javax.inject.Inject;

/**
 *
 * Created by liu on 18-5-25.
 */

public class Service {

    @Inject
    public Service() {
    }

    public void print() {
        System.out.println("call Service print method");
    }

}
