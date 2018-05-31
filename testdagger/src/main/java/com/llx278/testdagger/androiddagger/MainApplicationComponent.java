package com.llx278.testdagger.androiddagger;

import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 *
 * Created by liu on 18-5-29.
 */

@Component(modules = {MainActivityModel.class,AndroidInjectionModule.class})
public interface MainApplicationComponent {
    void inject(MainApplication app);
}
