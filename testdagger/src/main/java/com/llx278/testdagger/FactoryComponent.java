package com.llx278.testdagger;

import dagger.Component;

/**
 * Created by liu on 18-5-25.
 */

@Component(modules = ServiceModel.class)
public interface FactoryComponent {

    void test(Client haha);

    void injectTo(Client1 client1);

}
