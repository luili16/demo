package com.llx278.testdagger;

import dagger.Component;

/**
 * Created by liu on 18-5-25.
 */

@Component
public interface FactoryComponent {

    void test(Client client);

}
