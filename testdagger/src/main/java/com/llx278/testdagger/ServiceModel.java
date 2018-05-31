package com.llx278.testdagger;

import dagger.Module;
import dagger.Provides;

/**
 *
 * Created by liu on 18-5-28.
 */

@Module
public class ServiceModel {

    @Provides
    Service provideService() {
        return new Service();
    }

    @Provides
    Service1 provideService1() {
        return new Service1();
    }
}
